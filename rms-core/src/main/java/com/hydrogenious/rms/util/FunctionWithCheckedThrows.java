package com.hydrogenious.rms.util;

@FunctionalInterface
public interface FunctionWithCheckedThrows<T, R, E extends Exception> {
    R apply(T t) throws E;
}
