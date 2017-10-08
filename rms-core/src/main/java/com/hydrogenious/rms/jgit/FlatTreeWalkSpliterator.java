package com.hydrogenious.rms.jgit;

import java.io.IOException;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.eclipse.jgit.treewalk.TreeWalk;

public final class FlatTreeWalkSpliterator implements Spliterator<GitBlob> {
    private final TreeWalk treeWalk;

    public FlatTreeWalkSpliterator(TreeWalk treeWalk) {
        this.treeWalk = treeWalk;
    }

    @Override
    public boolean tryAdvance(Consumer<? super GitBlob> action) {
        try {
            if (treeWalk.next()) {
                action.accept(new GitBlob(treeWalk));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Spliterator<GitBlob> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return DISTINCT | NONNULL | IMMUTABLE;
    }
}
