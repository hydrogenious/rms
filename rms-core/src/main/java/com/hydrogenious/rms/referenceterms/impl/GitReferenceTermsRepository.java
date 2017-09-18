package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.git.GitRepositories;
import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GitReferenceTermsRepository implements ReferenceTermsRepository {

    private final GitRepositories gitRepositories;

    @Autowired
    public GitReferenceTermsRepository(final GitRepositories gitRepositories) {
        this.gitRepositories = gitRepositories;
    }

    @Override
    public Set<ReferenceTerm> findAll() {
        return gitRepositories.getAll()
                .map(GitReferenceTerm::new)
                .collect(Collectors.<ReferenceTerm>toSet());
    }

}
