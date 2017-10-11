package com.hydrogenious.rms.git;

import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import org.eclipse.jgit.annotations.NonNull;

public interface GitRepository {
    @NonNull
    String path() throws GitRepositoryException;

    void commitFile(@NonNull String name, @NonNull String content) throws GitRepositoryException;
}
