package com.hydrogenious.rms.revision;

public interface Revision<T> {
    T getRevision();

    RevisionMeta getRevisionMeta();
}
