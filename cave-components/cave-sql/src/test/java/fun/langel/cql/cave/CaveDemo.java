package fun.langel.cql.cave;

import fun.langel.cql.annotation.Cave;
import fun.langel.cql.annotation.Param;
import fun.langel.cql.annotation.Select;

import java.util.List;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/9 21:24
 **/
@Cave
public interface CaveDemo {

    @Select(sql = "select field1,field2 from index1 where f1 = #{param1}", direct = "defaultEs")
    String query(@Param(name = "param1") String param1);


    @Select(sql = "select field1,field2 from demo_index where field1 = #{param1}", direct = "defaultEs", returnType = Model.class)
    List<Model> queryList(@Param(name = "param1") String param1);


    @Select(sql = "select fielda from demo_index where c_exists('fieldb') and c_script(`doc['safe@limit'].value>doc['available'].value`)")
    List<Model> queryListWithDefaultEs(@Param(name = "param1") String param1);

    @Select(sql = "select * from demo_index where fieldA = #{param2}")
    Object demoQuery(@Param(name = "param2") String param2);

    /**
     * BoolQueryBuilder condition1 = QueryBuilders.boolQuery();
     * condition1.must(QueryBuilders.existsQuery("SafeInventory@lowerLimit"));
     * condition1.must(QueryBuilders.scriptQuery(new Script("doc['SafeInventory@lowerLimit'].value>doc['available_quantity'].value")));
     * should.should(condition1);
     *
     * @return
     */
    @Select(sql = "select * from demo_index where c_exists('SafeInventory@lowerLimit') and " + "c_script('doc['SafeInventory@lowerLimit'].value>doc['available_quantity'].value')")
    List<Model> queryCase();

    @Select(sql = "select avg('field1') as n_field1,c_keyvalue(field2,'key1') as n_field2" + " from demo_index where c_exists('SafeInventory@lowerLimit')")
    int demoAvg();


    @Select(sql = "select count('fieldC') from demo_index where fieldC=3.5")
    int demoCount();
}
