package com.hydrogenious.rms.revision.impl;

import com.hydrogenious.rms.revision.Author;
import com.hydrogenious.rms.revision.RevisionMeta;
import java.util.Date;

public final class EmptyRevisionMeta implements RevisionMeta {
    @Override
    public Date getRevisionDate() {
        return new Date(0);
    }

    @Override
    public String getMessage() {
        return "No message";
    }

    @Override
    public Author getAuthor() {
        return null;
    }
}
