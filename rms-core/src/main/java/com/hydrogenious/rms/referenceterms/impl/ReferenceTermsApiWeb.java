package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Set;

@RestController
public class ReferenceTermsApiWeb implements ReferenceTermsApi {

    @GetMapping("/reference-terms")
    @Override
    public Set<ReferenceTerm> findAllReferenceTerms() {
        // @todo #2:30m Add ReferenceTerm repository
        return Collections.emptySet();
    }
}
