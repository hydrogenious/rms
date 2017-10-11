package com.hydrogenious.rms.git;

import java.util.stream.Stream;

import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.lib.Repository;

public interface GitRepositories {
    Stream<Repository> getAll();
    @NonNull
    GitRepository repository(@NonNull String name);
}
