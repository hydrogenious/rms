package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirements;
import org.eclipse.jgit.annotations.NonNull;

public final class GitReferenceTerm implements ReferenceTerm {

    private final String repository;
    private final String name;

    public GitReferenceTerm(@NonNull final String repository, @NonNull final String name) {
        this.repository = repository;
        this.name = name;
    }

    @Override
    public Requirements requirements() {
        throw new UnsupportedOperationException();
    }
}
