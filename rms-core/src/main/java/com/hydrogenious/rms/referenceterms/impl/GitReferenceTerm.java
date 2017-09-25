package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirement;
import com.hydrogenious.rms.requirement.impl.GitRequirement;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GitReferenceTerm implements ReferenceTerm {

    public static final String MASTER_HEAD = "refs/heads/master";
    public static final String REQUIREMENTS_PATH = "requirements";
    private final Repository repository;

    public GitReferenceTerm(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Requirement> getRequirements() {
        return tryGetMasterHead()
                .flatMap(this::tryStartFlatWalk)
                .map(this::getObjectIdStream)
                .orElse(Stream.empty())
                .map(this::toRequirement)
                .collect(Collectors.toSet());
    }

    private Requirement toRequirement(ObjectId objectId) {
        return new GitRequirement(repository, objectId);
    }

    private Stream<ObjectId> getObjectIdStream(TreeWalk walk) {
        Stream.Builder<ObjectId> ids = Stream.builder();

        try {
            walk.enterSubtree();

            while (walk.next()) {
                ObjectId id = walk.getObjectId(0);
                ids.accept(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ids.build();
    }

    private Optional<TreeWalk> tryStartFlatWalk(Ref head) {
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevTree masterTree = revWalk.parseCommit(head.getObjectId()).getTree();
            return Optional.ofNullable(TreeWalk.forPath(repository, REQUIREMENTS_PATH, masterTree));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<Ref> tryGetMasterHead() {
        try {
            return Optional.ofNullable(repository.findRef(MASTER_HEAD));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
