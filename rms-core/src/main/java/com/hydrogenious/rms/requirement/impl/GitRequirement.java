package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.requirement.Requirement;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

// @todo 2:00m create generic interface for Revision and retrieve revision history for requirement
public class GitRequirement implements Requirement {
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
}
