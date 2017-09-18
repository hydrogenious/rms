package com.hydrogenious.rms.git;

import org.eclipse.jgit.lib.Repository;

import java.util.stream.Stream;

public interface GitRepositories {
    Stream<Repository> getAll();
}
