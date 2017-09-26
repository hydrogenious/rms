package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepositories;
import com.hydrogenious.rms.util.DoSafe;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public final class FileSystemGitRepositories implements GitRepositories {

    private final File repositoriesRoot;

    @Autowired
    public FileSystemGitRepositories(@Value("${repositoriesRootPath}") final String repositoriesRootPath) {
        this.repositoriesRoot = new File(repositoriesRootPath);
    }

    @Override
    public Stream<Repository> getAll() {
        final File[] subFolders = repositoriesRoot.listFiles();

        if (subFolders == null) {
            return Stream.empty();
        }

        return Stream.of(subFolders)
                .parallel()
                .map(this::tryGetRepository)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private Optional<Repository> tryGetRepository(final File folder) {
        return Optional.ofNullable(RepositoryCache.FileKey.resolve(folder, FS.DETECTED))
                .map(it -> RepositoryCache.FileKey.lenient(it, FS.DETECTED))
                .flatMap(DoSafe.tryDo(key -> new FileRepository(key.getFile())));
    }
}
