package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

public class ReferenceTermsApiWebTest {

    @Test
    public void findAllReferenceTerms() {
        final ReferenceTermsApiWeb referenceTermsApi = new ReferenceTermsApiWeb();
        final Set<ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();
        Assert.assertThat(allReferenceTerms, Is.is(Collections.emptySet()));
    }
}
