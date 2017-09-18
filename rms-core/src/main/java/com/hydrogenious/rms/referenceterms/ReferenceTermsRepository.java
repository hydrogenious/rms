package com.hydrogenious.rms.referenceterms;

import java.util.Set;

public interface ReferenceTermsRepository {
    Set<ReferenceTerm> findAll();
}
