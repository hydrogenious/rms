package com.hydrogenious.rms.requirement;

import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import org.eclipse.jgit.annotations.NonNull;

public interface RequirementRepository {
    void save(@NonNull String referenceTermName, @NonNull RequirementDto requirementDto,
              @NonNull String message) throws GitRepositoryException;
}
