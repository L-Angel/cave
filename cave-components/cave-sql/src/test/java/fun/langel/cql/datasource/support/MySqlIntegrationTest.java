package fun.langel.cql.datasource.support;

import fun.langel.cql.IntegrationTest;
import fun.langel.cql.bind.Arg;
import fun.langel.cql.constant.Const;
import fun.langel.cql.datasource.Session;
import fun.langel.cql.parameter.Parameter;
import fun.langel.cql.parameter.ParameterResolver;
import fun.langel.cql.rv.ReturnValue;
import fun.langel.cql.rv.Rows;
import fun.langel.cql.util.Pair;
import fun.langel.cql.util.Props;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Verifies that the JDBC path really passes MySQL syntax through, including
 * JOIN, CTE, window functions, IN (${list}) expansion and type mapping.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
@Category(IntegrationTest.class)
public class MySqlIntegrationTest {

    @ClassRule
    public static final MySQLContainer<?> MYSQL = new MySQLContainer<>(
            DockerImageName.parse("public.ecr.aws/docker/library/mysql:8.0.36")
                    .asCompatibleSubstituteFor("mysql"))
            .withDatabaseName("cave")
            .withUsername("cave")
            .withPassword("cave")
            .withUrlParam("useSSL", "false")
            .withUrlParam("allowPublicKeyRetrieval", "true");

    private static JdbcDataSource DATA_SOURCE;

    @BeforeClass
    public static void configureDatasource() throws Exception {
        Props.setOverride(Const.CAVE_MYSQL_URL, null);
        Props.setOverride(Const.CAVE_MYSQL_HOST, MYSQL.getHost() + ":" + MYSQL.getMappedPort(3306));
        Props.setOverride(Const.CAVE_MYSQL_DBNAME, MYSQL.getDatabaseName());
        Props.setOverride(Const.CAVE_MYSQL_USERNAME, MYSQL.getUsername());
        Props.setOverride(Const.CAVE_MYSQL_PASSWORD, MYSQL.getPassword());
        Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, "useSSL=false&allowPublicKeyRetrieval=true");

        try (java.sql.Connection connection = DriverManager.getConnection(
                MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
             Statement statement = connection.createStatement()) {
            statement.execute("create table users ("
                    + "id bigint primary key, name varchar(64), score decimal(10,2), "
                    + "active boolean, created timestamp)");
            statement.execute("create table orders (id bigint primary key, user_id bigint)");
            statement.execute("insert into users values "
                    + "(1,'alice',12.50,true,'2024-01-02 03:04:05'),"
                    + "(2,'bob',5.00,false,'2024-02-03 04:05:06'),"
                    + "(3,'carol',20.00,true,'2024-03-04 05:06:07')");
            statement.execute("insert into orders values (10,1),(11,1),(12,3)");
        }
        DATA_SOURCE = new JdbcDataSource();
    }

    @Before
    public void resetData() throws Exception {
        try (java.sql.Connection connection = DriverManager.getConnection(
                MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
             Statement statement = connection.createStatement()) {
            statement.execute("update users set score=12.50, active=true where id=1");
            statement.execute("update users set score=5.00, active=false where id=2");
            statement.execute("update users set score=20.00, active=true where id=3");
        }
    }

    @AfterClass
    public static void clearDatasource() {
        if (DATA_SOURCE != null) {
            DATA_SOURCE.close();
        }
        Props.setOverride(Const.CAVE_MYSQL_URL, null);
        Props.setOverride(Const.CAVE_MYSQL_HOST, null);
        Props.setOverride(Const.CAVE_MYSQL_DBNAME, null);
        Props.setOverride(Const.CAVE_MYSQL_USERNAME, null);
        Props.setOverride(Const.CAVE_MYSQL_PASSWORD, null);
        Props.setOverride(Const.CAVE_MYSQL_URL_PARAMS, null);
    }

    @Test
    public void supportsJoinGroupByAndHaving() {
        Rows rows = (Rows) query("select u.name, count(o.id) as orders "
                + "from users u left join orders o on o.user_id = u.id "
                + "group by u.name having count(o.id) > 0 order by orders desc");

        Assert.assertEquals(2, rows.getValue().size());
        Assert.assertEquals("alice", rows.getValue().get(0).getValue().get("name").getValue());
        Assert.assertEquals(2,
                ((fun.langel.cql.rv.Number) rows.getValue().get(0).getValue().get("orders")).intValue());
    }

    @Test
    public void supportsCteAndWindowFunctions() {
        Rows rows = (Rows) query("with high as (select * from users where score > 10) "
                + "select name, row_number() over (order by score desc) as rn from high order by rn");

        Assert.assertEquals(2, rows.getValue().size());
        Assert.assertEquals("carol", rows.getValue().get(0).getValue().get("name").getValue());
        Assert.assertEquals(1,
                ((fun.langel.cql.rv.Number) rows.getValue().get(0).getValue().get("rn")).intValue());
    }

    @Test
    public void expandsInListForJdbc() {
        Arg[] args = {new Arg("ids", "ids", Arrays.asList(1L, 3L), List.class)};
        Pair<String, List<Parameter>> pair = new ParameterResolver().resolveForJdbc(
                "select name from users where id in (${ids}) order by id", args);

        Session session = DATA_SOURCE.getConnection().getSession();
        try {
            ((fun.langel.cql.datasource.PreparedSession) session).setParameters(pair.right());
            Rows rows = (Rows) session.executeQuery(pair.left());
            List<?> names = (List<?>) rows.mapTo(String.class);
            Assert.assertEquals(Arrays.asList("alice", "carol"), names);
        } finally {
            session.close();
        }
    }

    @Test
    public void bindsDmlParametersAndMapsTypes() {
        Session session = DATA_SOURCE.getConnection().getSession();        try {
            Pair<String, List<Parameter>> pair = new ParameterResolver().resolveForJdbc(
                    "update users set score = #{score} where id = #{id}",
                    new Arg[]{
                            new Arg("score", "score", new BigDecimal("99.90"), BigDecimal.class),
                            new Arg("id", "id", 2L, Long.class)
                    });
            ((fun.langel.cql.datasource.PreparedSession) session).setParameters(pair.right());
            Number affected = session.executeUpdate(pair.left());
            Assert.assertEquals(1, affected.intValue());
        } finally {
            session.close();
        }

        Rows rows = (Rows) query("select score, active, created from users where id = 2");
        List<?> mapped = (List<?>) rows.mapTo(User.class);
        User user = (User) mapped.get(0);
        Assert.assertEquals(new BigDecimal("99.90"), user.score);
        Assert.assertFalse(user.active);
        Assert.assertEquals(LocalDateTime.of(2024, 2, 3, 4, 5, 6), user.created);
    }

    private ReturnValue<?> query(String sql) {
        Session session = DATA_SOURCE.getConnection().getSession();
        try {
            return session.executeQuery(sql);
        } finally {
            session.close();
        }
    }

    public static class User {
        private BigDecimal score;
        private boolean active;
        private LocalDateTime created;
    }
}
