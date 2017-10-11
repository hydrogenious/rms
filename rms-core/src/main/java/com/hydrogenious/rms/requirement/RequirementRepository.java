package com.hydrogenious.rms.requirement;

import org.eclipse.jgit.annotations.NonNull;

public interface RequirementRepository {
    void save(@NonNull String referenceTermName, @NonNull RequirementDto requirementDto);
}
