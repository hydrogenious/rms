package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.git.GitRepositories;
import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import com.hydrogenious.rms.requirement.RequirementDto;
import com.hydrogenious.rms.requirement.RequirementRepository;
import org.eclipse.jgit.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GitRequirementRepository implements RequirementRepository {

    private final GitRepositories gitRepositories;

    @Autowired
    public GitRequirementRepository(@NonNull final GitRepositories gitRepositories) {
        this.gitRepositories = gitRepositories;
    }

    @Override
    public void save(@NonNull final String referenceTermName, @NonNull final RequirementDto requirementDto,
                     @NonNull final String message) throws GitRepositoryException {
        final GitRepository gitRepository = gitRepositories.repository(referenceTermName);
        gitRepository.commitFile(requirementDto.getName(), requirementDto.getContent(), message);
    }
}
