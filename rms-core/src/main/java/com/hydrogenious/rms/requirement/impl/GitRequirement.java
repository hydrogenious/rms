package com.hydrogenious.rms.requirement.impl;

import com.hydrogenious.rms.jgit.GitBlob;
import com.hydrogenious.rms.requirement.Requirement;
import com.hydrogenious.rms.revision.Revision;
import com.hydrogenious.rms.revision.impl.EmptyRevisionMeta;
import com.hydrogenious.rms.revision.impl.RequirementRevision;
import com.hydrogenious.rms.util.DoSafe;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevSort;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

public final class GitRequirement implements Requirement {
    private final Repository repository;
    private final GitBlob blob;

    public GitRequirement(final Repository repository, final GitBlob blob) {
        this.repository = repository;
        this.blob = blob;
    }

    @Override
    public byte[] toByteArray() throws IOException {
        ObjectLoader objectLoader = repository.open(blob.getObjectId());
        return objectLoader.getBytes();
    }

    @Override
    public Stream<Revision<Requirement>> getRevisions() {
        try (RevWalk revWalk = new RevWalk(repository)) {
            try {
                RevCommit revCommit = revWalk.parseCommit(repository.exactRef("refs/heads/master").getObjectId());
                revWalk.markStart(revCommit);
            } catch (IOException e) {
                e.printStackTrace();
            }

            revWalk.sort(RevSort.COMMIT_TIME_DESC);
            revWalk.setTreeFilter(AndTreeFilter.create(
                TreeFilter.ANY_DIFF,
                PathFilter.create(blob.getPath())
            ));

            return StreamSupport.stream(revWalk.spliterator(), false)
                .map(DoSafe.tryDo(this::startWalkOnCommit))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GitBlob::new)
                .map(this::toRequirementRevision);
        }
    }

    private TreeWalk startWalkOnCommit(RevCommit commit) throws IOException {
        return TreeWalk.forPath(repository, blob.getPath(), commit.getTree());
    }

    // @todo #1:00m provide correct revision meta
    private Revision<Requirement> toRequirementRevision(GitBlob gitBlob) {
        return new RequirementRevision(new GitRequirement(repository, gitBlob), new EmptyRevisionMeta());
    }

    public String getName() {
        return blob.getName();
    }
}
