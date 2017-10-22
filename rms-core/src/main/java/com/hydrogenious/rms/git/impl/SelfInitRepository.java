package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import java.io.File;
import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.util.FS;

/**
 * Декоратор над репозиторием, если репозитория не существует, то он его создаст перед выполнением
 * любой операции над репозиторием
 */
public class SelfInitRepository implements GitRepository {
    private final GitRepository origin;

    public SelfInitRepository(@NonNull final GitRepository origin) {
        this.origin = origin;
    }

    @Override
    public String path() {
        lazyInit();
        return origin.path();
    }

    @Override
    public void commitFile(@NonNull final String name, @NonNull final String content,
                           @NonNull final String message) {
        lazyInit();
        origin.commitFile(name, content, message);
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
