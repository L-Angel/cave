package fun.langel.cql.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2022/8/2 10:41
 **/
public class NumberUtil {

    private NumberUtil() {
    }

    public static boolean isLongOrInteger(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        Pattern pattern = Pattern.compile("[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
