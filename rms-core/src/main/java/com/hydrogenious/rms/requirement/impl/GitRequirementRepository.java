package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.git.GitRepositories;
import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.requirement.RequirementDto;
import com.hydrogenious.rms.requirement.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GitRequirementRepository implements RequirementRepository {

    private final GitRepositories gitRepositories;

    @Autowired
    public GitRequirementRepository(GitRepositories gitRepositories) {
        this.gitRepositories = gitRepositories;
    }

    @Override
    public void save(String referenceTermsName, RequirementDto requirementDto) {
         final GitRepository gitRepository = gitRepositories.repository(referenceTermsName);
        // @todo #6:30m add commitFile to GitRepository
        // gitRepository.commitFile(requirementDto.getName(), requirementDto.getContent());
    }
}
