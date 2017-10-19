package com.hydrogenious.rms.git.impl;

import com.hydrogenious.rms.git.GitRepository;
import com.hydrogenious.rms.git.exceptions.GitRepositoryException;
import java.io.File;
import java.io.IOException;
import org.cactoos.io.InputOf;
import org.cactoos.io.LengthOf;
import org.cactoos.io.OutputTo;
import org.cactoos.io.TeeInput;
import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

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
        try (
            Repository repository = new FileRepositoryBuilder()
                .setGitDir(new File(path, ".git"))
                .readEnvironment()
                .findGitDir()
                .build();
            Git git = new Git(repository)
        ) {
            final File file = new File(repository.getDirectory().getParent(), name);
            if (!file.createNewFile()) {
                throw new IOException("Could not create file " + file);
            }
            new LengthOf(
                new TeeInput(
                    new InputOf(content),
                    new OutputTo(file)
                )
            ).value();

            git.add()
                .addFilepattern(name)
                .call();

            git.commit()
                .setMessage("some message")
                .call();
        } catch (IOException | GitAPIException e) {
            throw new GitRepositoryException(e);
        }
    }
}
