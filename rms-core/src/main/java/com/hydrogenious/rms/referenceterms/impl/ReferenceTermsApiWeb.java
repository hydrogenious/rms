package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsApi;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public final class ReferenceTermsApiWeb implements ReferenceTermsApi {

    private final ReferenceTermsRepository referenceTermsRepository;

    @Autowired
    public ReferenceTermsApiWeb(final ReferenceTermsRepository referenceTermsRepository) {
        this.referenceTermsRepository = referenceTermsRepository;
    }

    @GetMapping("/reference-terms")
    @Override
    public Set<? extends ReferenceTerm> findAllReferenceTerms() {
        return referenceTermsRepository.findAll();
    }
}
