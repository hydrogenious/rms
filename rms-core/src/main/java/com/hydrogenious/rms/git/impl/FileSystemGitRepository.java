package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import org.eclipse.jgit.annotations.NonNull;

public class FileSystemGitRepository implements GitRepository {
    private final String path;

    public FileSystemGitRepository(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public void commitFile(@NonNull final String name, @NonNull final String content) throws GitRepositoryException {

    }
}
