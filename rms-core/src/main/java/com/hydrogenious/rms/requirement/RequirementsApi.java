package com.hydrogenious.rms.requirement;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.jgit.annotations.NonNull;

public interface RequirementsApi {
    void save(@NonNull String referenceTermName, @NonNull ObjectNode requirementJson);
}
