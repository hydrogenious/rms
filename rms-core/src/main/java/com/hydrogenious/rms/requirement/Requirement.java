package com.hydrogenious.rms.requirement;

import java.io.IOException;

/**
 * Требование
 */
public interface Requirement {
    byte[] toByteArray() throws IOException;
}
