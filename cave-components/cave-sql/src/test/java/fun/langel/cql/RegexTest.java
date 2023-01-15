package fun.langel.cql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/5/16 16:54
 **/
@RunWith(JUnit4.class)
public class RegexTest {

    private static final String REGEX = "[#\\$]\\{.*?}";

    @Test
    public void test_regex() {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher("select * from table1 where field1 = ${value1} and field2 = ${value2} and ${} and #{value4}");

        while (matcher.find()) {
//            String replaceResult = matcher.replaceFirst("?");
//            System.out.println(replaceResult);
            System.out.println(matcher.group());
        }
        final String s = matcher.replaceAll("?");
        System.out.println(s);
    }
}
