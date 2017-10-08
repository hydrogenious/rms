package com.hydrogenious.rms.revision;

import java.util.Date;

public interface RevisionMeta {
    Date getRevisionDate();

    String getMessage();

    Author getAuthor();
}
