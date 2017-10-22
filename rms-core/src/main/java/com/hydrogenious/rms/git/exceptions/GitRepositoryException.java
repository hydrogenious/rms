package com.hydrogenious.rms.git.exceptions;

public class GitRepositoryException extends RuntimeException {
    public GitRepositoryException(Throwable throwable) {
        super(throwable);
    }
}
