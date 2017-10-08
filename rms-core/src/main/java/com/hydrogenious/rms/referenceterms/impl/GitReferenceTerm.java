package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.jgit.FlatTreeWalkSpliterator;
import com.hydrogenious.rms.jgit.GitBlob;
import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirement;
import com.hydrogenious.rms.requirement.impl.GitRequirement;
import com.hydrogenious.rms.util.DoSafe;
import com.hydrogenious.rms.util.SupplySafe;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

public final class GitReferenceTerm implements ReferenceTerm {

    public static final String MASTER_HEAD = "refs/heads/master";
    public static final String REQUIREMENTS_PATH = "requirements";
    private final Repository repository;

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

    private Requirement toRequirement(GitBlob blob) {
        return new GitRequirement(repository, blob);
    }

    private Stream<GitBlob> getObjectIdStream(TreeWalk walk) {
        return StreamSupport.stream(new FlatTreeWalkSpliterator(walk), false);
    }

    private TreeWalk startFlatWalk(Ref head) throws IOException {
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevTree masterTree = revWalk.parseCommit(head.getObjectId()).getTree();
            TreeWalk treeWalk = TreeWalk.forPath(repository, REQUIREMENTS_PATH, masterTree);
            treeWalk.enterSubtree();
            return treeWalk;
        }
    }

    private Ref getMasterHead() throws IOException {
        return repository.findRef(MASTER_HEAD);
    }
}
