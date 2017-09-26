package com.hydrogenious.rms.util;

import java.util.Optional;
import java.util.function.Supplier;

public final class SupplySafe<T, E extends Exception> implements Supplier<Optional<T>> {

    private final SupplierWithCheckedThrows<T, E> delegate;

    public SupplySafe(SupplierWithCheckedThrows<T, E> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<T> get() {
        try {
            return Optional.ofNullable(delegate.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static <T, E extends Exception>
    Supplier<Optional<T>> trySupply(SupplierWithCheckedThrows<T, E> delegate) {
        return new SupplySafe<>(delegate);
    }
}
