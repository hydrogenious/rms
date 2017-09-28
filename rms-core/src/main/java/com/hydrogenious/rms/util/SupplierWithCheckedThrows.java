package com.hydrogenious.rms.util;

@FunctionalInterface
public interface SupplierWithCheckedThrows<T, E extends Exception> {
    T get() throws E;
}
