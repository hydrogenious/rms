package com.hydrogenious.rms.requirement.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hydrogenious.rms.requirement.RequirementsApi;
import java.nio.file.Paths;
import org.eclipse.jgit.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequirementsApiWeb implements RequirementsApi {

    private final String repositoriesRootPath;

    @Autowired
    public RequirementsApiWeb(@Value("${repositoriesRootPath}") @NonNull final String repositoriesRootPath) {
        this.repositoriesRootPath = repositoriesRootPath;
    }

    @PutMapping("/requirements/{referenceTermName}")
    @Override
    public void save(@PathVariable("referenceTermName") @NonNull final String referenceTermName,
                     @RequestBody @NonNull final ObjectNode requirementJson) {
        new GitRequirement(
            Paths.get(repositoriesRootPath, referenceTermName).toString(),
            requirementJson
        ).update();
    }
}
