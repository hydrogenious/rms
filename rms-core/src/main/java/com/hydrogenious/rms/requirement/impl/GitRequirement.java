package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.requirement.Requirement;
import java.io.IOException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;

// @todo #2:00m create generic interface for Revision and retrieve revision history for requirement
public final class GitRequirement implements Requirement {
    private final Repository repository;
    private final ObjectId objectId;

    public GitRequirement(final Repository repository, final ObjectId objectId) {
        this.repository = repository;
        this.objectId = objectId;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ObjectLoader objectLoader = repository.open(objectId);
        return objectLoader.getBytes();
    }

    public String getName() {
        return objectId.getName();
    }
}
