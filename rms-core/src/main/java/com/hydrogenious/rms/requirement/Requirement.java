package com.hydrogenious.rms.requirement;

import com.hydrogenious.rms.revision.Revision;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Требование.
 */
public interface Requirement {
    byte[] toByteArray() throws IOException;

    Stream<Revision<Requirement>> getRevisions();
}
