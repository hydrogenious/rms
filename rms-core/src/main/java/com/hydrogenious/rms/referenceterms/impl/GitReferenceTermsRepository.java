package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GitReferenceTermsRepository implements ReferenceTermsRepository {

    private final File repositoriesRoot;
    private final FS fileSystem;

    @Autowired
    public GitReferenceTermsRepository(@Value("${repositoriesRootPath}") String repositoriesRootPath, FS fileSystem) {
        this.repositoriesRoot = new File(repositoriesRootPath);
        this.fileSystem = fileSystem;
    }

    @Override
    public Set<ReferenceTerm> findAll() {
        File[] subFolders = repositoriesRoot.listFiles();

        if (subFolders == null) {
            return Collections.emptySet();
        }

        return Stream.of(subFolders)
                .parallel()
                .map(this::tryGetRepository)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GitReferenceTerm::new)
                .collect(Collectors.<ReferenceTerm>toSet());
    }

    private Optional<Repository> tryGetRepository(File folder) {
        return Optional.ofNullable(RepositoryCache.FileKey.resolve(folder, fileSystem))
                .map(it -> RepositoryCache.FileKey.lenient(it, fileSystem))
                .flatMap(key -> {
                    try {
                        return Optional.of(RepositoryCache.open(key));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Optional.empty();
                });
    }
}
