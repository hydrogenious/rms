package com.hydrogenious.rms.referenceterms;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ReferenceTermsApi {
    ObjectNode findAllReferenceTerms();
}
