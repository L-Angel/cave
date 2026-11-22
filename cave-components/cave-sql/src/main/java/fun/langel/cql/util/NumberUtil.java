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
        if (str == null || str.isEmpty()) return false;
        int start = (str.charAt(0) == '-' || str.charAt(0) == '+') ? 1 : 0;
        if (start == str.length()) return false;
        for (int idx = start; idx < str.length(); idx++) {
            if (!Character.isDigit(str.charAt(idx))) return false;
        }
        return true;
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        Pattern pattern = Pattern.compile("[-+]?[0-9]+[.]{0,1}[0-9]*[dD]{0,1}");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
