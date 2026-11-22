/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.rv;

import fun.langel.cql.exception.MappingException;

import java.util.Base64;

/**
 * Binary return value for MySQL BLOB / VARBINARY columns.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public class Bytes implements ReturnValue<byte[]> {

    private final byte[] value;

    private Bytes(final byte[] value) {
        this.value = value;
    }

    public static Bytes of(final byte[] value) {
        return new Bytes(value);
    }

    @Override
    public byte[] getValue() {
        return this.value;
    }

    @Override
    public Object mapTo(Class<?> klass) throws MappingException {
        if (klass.equals(byte[].class)) {
            return this.value;
        }
        if (klass.equals(java.lang.String.class)) {
            return Base64.getEncoder().encodeToString(this.value);
        }
        throw new MappingException("Cannot map binary data to " + klass.getName());
    }
}
