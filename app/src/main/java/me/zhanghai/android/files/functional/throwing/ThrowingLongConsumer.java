/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package me.zhanghai.android.files.functional.throwing;

import java.util.Objects;

import me.zhanghai.android.files.functional.FunctionalException;
import me.zhanghai.android.files.functional.compat.LongConsumer;

/**
 * Represents an operation that accepts a single {@code long}-valued argument and
 * returns no result.  This is the primitive type specialization of
 * {@link ThrowingConsumer} for {@code long}.  Unlike most other functional interfaces,
 * {@code ThrowingLongConsumer} is expected to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(long)}.
 *
 * @see ThrowingConsumer
 * @since 1.8
 */
@FunctionalInterface
public interface ThrowingLongConsumer extends LongConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void acceptThrows(long value) throws Exception;

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    default void accept(long value) {
        try {
            acceptThrows(value);
        } catch (Exception e) {
            throw new FunctionalException(e);
        }
    }

    /**
     * Returns a composed {@code ThrowingLongConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code ThrowingLongConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default ThrowingLongConsumer andThen(ThrowingLongConsumer after) {
        Objects.requireNonNull(after);
        return (long t) -> { acceptThrows(t); after.acceptThrows(t); };
    }
}
