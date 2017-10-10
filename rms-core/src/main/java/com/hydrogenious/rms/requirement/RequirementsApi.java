package com.hydrogenious.rms.requirement;

import org.eclipse.jgit.annotations.NonNull;

public interface RequirementsApi {
    void save(@NonNull String referenceTermsName, @NonNull RequirementDto requirementDto);
}
