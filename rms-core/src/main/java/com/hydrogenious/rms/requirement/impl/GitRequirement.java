package com.hydrogenious.rms.requirement.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hydrogenious.rms.git.impl.FileSystemGitRepository;
import com.hydrogenious.rms.git.impl.SelfInitRepository;
import com.hydrogenious.rms.requirement.Requirement;
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;
import org.eclipse.jgit.annotations.NonNull;

// @todo #2:00m create generic interface for Revision and retrieve revision history for requirement
public final class GitRequirement implements Requirement {

    private final String repository;
    private final Scalar<String> name;
    private final Scalar<String> content;

    public GitRequirement(@NonNull final String repository, @NonNull final ObjectNode requirementJson) {
        this.repository = repository;
        this.name = () -> requirementJson.findValue("name").asText();
        this.content = () -> requirementJson.findValue("content").asText();
    }

    @Override
    public void update() {
        new SelfInitRepository(new FileSystemGitRepository(repository))
            .commitFile(
                new UncheckedScalar<>(name).value(),
                new UncheckedScalar<>(content).value(),
                "updated"
            );
    }
}
