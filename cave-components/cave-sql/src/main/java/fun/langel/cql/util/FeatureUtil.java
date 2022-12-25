/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/21 01:47
 **/
public class FeatureUtil {
    private static final String END = ";";
    private static final String ASSIGN = ":";
    private static final String R_END = "#3A";
    private static final String R_ASSIGN = "#3B";

    private FeatureUtil() {
    }

    public static Map<String, String> fromString(String str) {

        Map<String, String> attrs = new LinkedHashMap();
        if (isNotBlank(str)) {
            String[] arr = str.split(END);
            int var4 = arr.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String kv = arr[var5];
                if (isNotBlank(kv)) {
                    String[] ar = kv.split(ASSIGN);
                    if (ar.length == 2) {
                        String key = decode(ar[0]);
                        String val = decode(ar[1]);
                        if (!StringUtil.isEmpty(val)) {
                            attrs.put(key, val);
                        }
                    }
                }
            }
        }

        return attrs;
    }

    private static boolean isNotBlank(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }


    private static String encode(String val) {
        return replace(replace(val, END, R_END), ASSIGN, R_ASSIGN);
    }

    private static String decode(String val) {
        return replace(replace(val, R_END, END), R_ASSIGN, ASSIGN);
    }

    private static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    private static String replace(String text, String repl, String with, int max) {
        if (text != null && repl != null && with != null && repl.length() != 0 && max != 0) {
            StringBuilder buf = new StringBuilder(text.length());
            int start = 0;

            int end;
            while ((end = text.indexOf(repl, start)) != -1) {
                buf.append(text, start, end).append(with);
                start = end + repl.length();
                --max;
                if (max == 0) {
                    break;
                }
            }

            buf.append(text.substring(start));
            return buf.toString();
        } else {
            return text;
        }
    }

}
