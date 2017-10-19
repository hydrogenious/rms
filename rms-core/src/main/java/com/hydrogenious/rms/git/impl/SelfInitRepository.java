package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.util.FS;

import java.io.File;

/**
 * Декоратор над репозиторием, если репозитория не существует, то он его создаст перед выполнением
 * любой операции над репозиторием
 */
public class SelfInitRepository implements GitRepository {
    private final GitRepository origin;

    public SelfInitRepository(GitRepository origin) {
        this.origin = origin;
    }

    @Override
    public String path() throws GitRepositoryException {
        lazyInit();
        return origin.path();
    }

    @Override
    public void commitFile(String name, String content) throws GitRepositoryException {
        lazyInit();
        origin.commitFile(name, content);
    }

    private void lazyInit() throws GitRepositoryException {
        final File repositoryDirectory = new File(origin.path());
        if (!RepositoryCache.FileKey.isGitRepository(repositoryDirectory, FS.DETECTED)) {
            try {
                Git.init().setDirectory(repositoryDirectory).call();
            } catch (GitAPIException e) {
                throw new GitRepositoryException(e);
            }
        }
    }
}
