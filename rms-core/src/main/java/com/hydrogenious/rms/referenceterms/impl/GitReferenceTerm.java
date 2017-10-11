package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirement;
import com.hydrogenious.rms.requirement.impl.GitRequirement;
import com.hydrogenious.rms.util.DoSafe;
import com.hydrogenious.rms.util.SupplySafe;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

public final class GitReferenceTerm implements ReferenceTerm {

    public static final String MASTER_HEAD = "refs/heads/master";
    public static final String REQUIREMENTS_PATH = "requirements";
    private final Repository repository;

    // @todo #7:60m Add GitRepository as dependency, not simple Repository
    public GitReferenceTerm(final Repository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Requirement> getRequirements() {
        return SupplySafe.trySupply(this::getMasterHead).get()
            .flatMap(DoSafe.tryDo(this::startFlatWalk))
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

    private TreeWalk startFlatWalk(Ref head) throws IOException {
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevTree masterTree = revWalk.parseCommit(head.getObjectId()).getTree();
            return TreeWalk.forPath(repository, REQUIREMENTS_PATH, masterTree);
        }
    }

    private Ref getMasterHead() throws IOException {
        return repository.findRef(MASTER_HEAD);
    }
}
