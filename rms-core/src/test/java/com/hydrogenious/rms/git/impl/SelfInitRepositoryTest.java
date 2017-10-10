package com.hydrogenious.rms.git.impl;

import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.*;

public class SelfInitRepositoryTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void pathWhenNotExistRepository() throws Exception {
        final String path = folder.getRoot().getPath();

        new SelfInitRepository(new FileSystemGitRepository(path)).path();

        final File[] files = folder.getRoot().listFiles();
        if (files != null) {
            assertThat(files.length, IsEqual.equalTo(1));
            assertThat(files[0].getName(), IsEqual.equalTo(".git"));
        } else {
            fail();
        }
    }

}