package com.hydrogenious.rms.referenceterms.impl;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hydrogenious.rms.referenceterms.ReferenceTermsApi;
import org.eclipse.jgit.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ReferenceTermsApiWeb implements ReferenceTermsApi {

    private final String repositoriesRootPath;

    @Autowired
    public ReferenceTermsApiWeb(@Value("${repositoriesRootPath}") @NonNull final String repositoriesRootPath) {
        this.repositoriesRootPath = repositoriesRootPath;
    }

    @GetMapping("/reference-terms")
    @Override
    public ObjectNode findAllReferenceTerms() {
        // @todo #28 new GitReferenceTerms(repositoriesRootPath).asJson()
        return new ObjectNode(JsonNodeFactory.instance);
    }
}
