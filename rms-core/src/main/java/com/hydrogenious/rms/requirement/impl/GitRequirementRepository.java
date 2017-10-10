package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.git.GitRepositories;
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
        // @todo #5:30m add create repository to gitRepositories
        // final GitRepository gitRepository = gitRepositories.getOrCreate(referenceTermsName);
        // @todo #6:30m add commitFile to GitRepository
        // gitRepository.commitFile(requirementDto.getName(), requirementDto.getContent());
    }
}
