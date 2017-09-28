package com.hydrogenious.rms.util;

import java.util.Optional;
import java.util.function.Function;

// @todo #0:30m add logger and log ignored exception
public final class DoSafe<T, R, E extends Exception> implements Function<T, Optional<R>> {
    private final FunctionWithCheckedThrows<T, R, E> delegate;

    private DoSafe(FunctionWithCheckedThrows<T, R, E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<R> apply(T t) {
        try {
            return Optional.ofNullable(delegate.apply(t));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static <T, R, E extends Exception> Function<T, Optional<R>> tryDo(
            FunctionWithCheckedThrows<T, R, E> delegate) {
        return new DoSafe<>(delegate);
    }
}
