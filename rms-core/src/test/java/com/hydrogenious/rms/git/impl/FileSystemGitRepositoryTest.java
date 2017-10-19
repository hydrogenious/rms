package com.hydrogenious.rms.git.impl;

import org.hamcrest.core.Is;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertThat;

public class FileSystemGitRepositoryTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void commitFile() throws Exception {
        final SelfInitRepository fileSystemRepository = new SelfInitRepository(
            new FileSystemGitRepository(folder.getRoot().getPath()));

        fileSystemRepository.commitFile("some_name", "some content");

        assertThat(folder.getRoot().listFiles()[1].getName(), Is.is("some_name"));
    }

}