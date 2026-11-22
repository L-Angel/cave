/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;

/**
 * Boolean return value used for MySQL BOOLEAN / BIT(1) columns.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class Boolean implements ReturnValue<java.lang.Boolean> {

    private final boolean value;

    private Boolean(final boolean value) {
        this.value = value;
    }

    public static Boolean of(final boolean value) {
        return new Boolean(value);
    }

    @Override
    public java.lang.Boolean getValue() {
        return this.value;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (klass.equals(java.lang.Boolean.class) || klass.equals(boolean.class)) {
            return this.value;
        }
        if (klass.equals(java.lang.String.class)) {
            return java.lang.String.valueOf(this.value);
        }
        if (klass.equals(Integer.class) || klass.equals(int.class)) {
            return this.value ? 1 : 0;
        }
        if (klass.equals(Long.class) || klass.equals(long.class)) {
            return this.value ? 1L : 0L;
        }
        if (klass.equals(Short.class) || klass.equals(short.class)) {
            return (short) (this.value ? 1 : 0);
        }
        throw new MappingException("Cannot map boolean to " + klass.getName());
    }
}
