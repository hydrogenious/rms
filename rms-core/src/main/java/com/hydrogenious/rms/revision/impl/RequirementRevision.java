package com.hydrogenious.rms.revision.impl;

import com.hydrogenious.rms.requirement.Requirement;
import com.hydrogenious.rms.revision.Revision;
import com.hydrogenious.rms.revision.RevisionMeta;

public final class RequirementRevision implements Revision<Requirement> {
    private final Requirement revision;
    private final RevisionMeta revisionMeta;

    public RequirementRevision(Requirement revision, RevisionMeta revisionMeta) {
        this.revision = revision;
        this.revisionMeta = revisionMeta;
    }

    @Override
    public Requirement getRevision() {
        return revision;
    }

    @Override
    public RevisionMeta getRevisionMeta() {
        return revisionMeta;
    }
}
