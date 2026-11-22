/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2023/1/13 15:08
 **/
public class Props {

    /**
     * Values supplied by embedded configuration (for example the Spring Boot
     * starter reading {@code application.yml}). Explicit JVM {@code -D}
     * properties and environment variables still take precedence.
     */
    private static final Map<String, String> OVERRIDES = new ConcurrentHashMap<>();

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
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }
        value = System.getenv(key);
        return value == null ? OVERRIDES.get(key) : value;
    }

    public static String getProperty(final String key, final String def) {
        String propV = getProperty(key);
        return propV == null ? def : propV;
    }

    public static void setProperty(final String key, final String v) {
        System.setProperty(key, v);
    }

    /**
     * Sets a low-precedence value used when neither a JVM system property nor
     * an environment variable is present.
     */
    public static void setOverride(final String key, final String v) {
        if (key == null) {
            return;
        }
        if (v == null) {
            OVERRIDES.remove(key);
        } else {
            OVERRIDES.put(key, v);
        }
    }

    public static void clearOverrides() {
        OVERRIDES.clear();
    }

}
