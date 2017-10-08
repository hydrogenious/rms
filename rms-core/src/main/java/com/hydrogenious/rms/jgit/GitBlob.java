package com.hydrogenious.rms.jgit;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.treewalk.TreeWalk;

public class GitBlob {
    private final ObjectId objectId;
    private final String name;
    private final String path;

    /**
     * Create blob on current position of walk
     *
     * @param treeWalk to get current blob from
     */
    public GitBlob(TreeWalk treeWalk) {
        this(treeWalk.getObjectId(0), treeWalk.getNameString(), treeWalk.getPathString());
    }

    public GitBlob(ObjectId objectId, String name, String path) {
        this.name = name;
        this.path = path;
        this.objectId = objectId;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
