package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepository;

public class FileSystemGitRepository implements GitRepository {
    private final String path;

    public FileSystemGitRepository(String path) {
        this.path = path;
    }

    @Override
    public String path() {
        return path;
    }
}
