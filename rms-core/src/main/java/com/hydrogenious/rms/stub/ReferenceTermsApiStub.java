package com.hydrogenious.rms.stub;

import com.hydrogenious.rms.api.ReferenceTermsApi;
import com.hydrogenious.rms.model.ReferenceTerm;

import java.util.Set;

public class ReferenceTermsApiStub implements ReferenceTermsApi {
    private final Set<ReferenceTerm> referenceTerms;

    public ReferenceTermsApiStub(Set<ReferenceTerm> referenceTerms) {
        this.referenceTerms = referenceTerms;
    }

    @Override
    public Set<ReferenceTerm> findAllReferenceTerms() {
        return referenceTerms;
    }
}
