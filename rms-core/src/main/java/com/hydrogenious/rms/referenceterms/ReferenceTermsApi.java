package com.hydrogenious.rms.referenceterms;

import java.util.Set;

public interface ReferenceTermsApi {
    Set<? extends ReferenceTerm> findAllReferenceTerms();
}
