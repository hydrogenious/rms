package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirement;
import org.eclipse.jgit.lib.Repository;

import java.util.Collections;
import java.util.Set;

public class GitReferenceTerm implements ReferenceTerm {

    private final Repository repository;

    public GitReferenceTerm(final Repository repository) {
        this.repository = repository;
    }

    // @todo #2h get and return requirements from git repository
    @Override
    public Set<Requirement> getRequirements() {
        return Collections.emptySet();
    }
}
