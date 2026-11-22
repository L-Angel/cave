/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.resolve.dialect;

import fun.langel.cql.dialect.Dialect;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @date 2022/12/15 22:10
 **/
public class RedisGetDialectResolver implements RedisDialectResolver<Object, Object> {

    @Override
    public Dialect<Object> resolve(Object statement) {
        return RedisDialectResolver.super.resolve(statement);
    }
}
