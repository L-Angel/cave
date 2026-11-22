/*
 * Copyright 2021-2026 Rick Jiang and Cave contributors.
 * SPDX-License-Identifier: Apache-2.0
 */

package fun.langel.cql.capability;

/**
 * Declares which logical CQL features a datasource dialect can execute.
 * Both dialect resolvers and documentation derive their support matrix from
 * this contract so capabilities do not silently drift apart.
 *
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 */
public interface DialectCapabilities {

    String dialect();

    boolean aggregations();

    boolean groupBy();

    boolean having();

    boolean distinct();

    boolean keyValue();

    boolean cExists();

    boolean cScript();
}
