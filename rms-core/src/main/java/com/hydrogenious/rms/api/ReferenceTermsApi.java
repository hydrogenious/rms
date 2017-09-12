package com.hydrogenious.rms.api;

import com.hydrogenious.rms.model.ReferenceTerm;

import java.util.Set;

public interface ReferenceTermsApi {
    Set<ReferenceTerm> findAllReferenceTerms();
}
