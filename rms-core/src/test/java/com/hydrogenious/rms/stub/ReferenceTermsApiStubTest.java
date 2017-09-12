package com.hydrogenious.rms.stub;

import com.hydrogenious.rms.model.ReferenceTerm;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;

public class ReferenceTermsApiStubTest {

    @Test
    public void findAllReferenceTerms() {
        final ReferenceTermsApiStub referenceTermsApi = new ReferenceTermsApiStub(Collections.emptySet());
        final Set<ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();
        assertThat(allReferenceTerms, Is.is(Collections.emptySet()));
    }
}
