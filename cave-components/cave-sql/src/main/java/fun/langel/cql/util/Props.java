/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package fun.langel.cql.util;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/1/13 15:08
 **/
public class Props {

    public static boolean getBoolean(final String k, boolean def) {
        String r = getProperty(k);
        if (r == null || r.isEmpty()) {
            return def;
        }
        return Boolean.parseBoolean(r);
    }

    public static boolean getBoolean(final String k) {
        return Boolean.parseBoolean(getProperty(k));
    }

    public static int getInt(final String k) {
        return getInt(k, 0);
    }

    public static int getInt(final String k, final int def) {
        String v = getProperty(k);
        return v == null || v.isEmpty() ? def : Integer.parseInt(v);
    }

    public static String getProperty(final String key) {
        return System.getProperty(key) == null ? System.getenv(key) : System.getProperty(key);
    }

    public static String getProperty(final String key, final String def) {
        String propV = getProperty(key);
        return propV == null ? def : propV;
    }

    public static void setProperty(final String key, final String v) {
        System.setProperty(key, v);
    }

}
