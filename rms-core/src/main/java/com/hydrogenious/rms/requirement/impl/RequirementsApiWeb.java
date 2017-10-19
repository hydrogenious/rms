package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import com.hydrogenious.rms.requirement.RequirementDto;
import com.hydrogenious.rms.requirement.RequirementRepository;
import com.hydrogenious.rms.requirement.RequirementsApi;
import org.eclipse.jgit.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequirementsApiWeb implements RequirementsApi {

    private final RequirementRepository requirementRepository;

    @Autowired
    public RequirementsApiWeb(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @PutMapping("/requirements")
    @Override
    public void save(@NonNull final String referenceTermsName,
                     @RequestBody @NonNull final RequirementDto requirementDto) {
        try {
            requirementRepository.save(referenceTermsName, requirementDto);
        } catch (GitRepositoryException e) {
            e.printStackTrace();
        }
    }
}
