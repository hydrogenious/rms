package com.hydrogenious.rms.git;

import org.eclipse.jgit.annotations.NonNull;

public interface GitRepository {
    @NonNull
    String path();

    void commitFile(@NonNull String name, @NonNull String content, String message);
}
