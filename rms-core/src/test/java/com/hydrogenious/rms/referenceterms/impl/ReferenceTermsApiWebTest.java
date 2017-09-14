package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ReferenceTermsApiWebTest {

    @Mock
    private ReferenceTermsRepository referenceTermsRepository;

    @Test
    public void findAllReferenceTerms() {
        doReturn(Collections.emptySet())
                .when(referenceTermsRepository).findAll();

        final ReferenceTermsApiWeb referenceTermsApi = new ReferenceTermsApiWeb(referenceTermsRepository);
        final Set<? extends ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();
        Assert.assertThat(allReferenceTerms, Is.is(Collections.emptySet()));
    }
}
