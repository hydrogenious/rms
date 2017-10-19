package com.hydrogenious.rms.git.impl;

import java.io.File;
import org.cactoos.text.TextOf;
import org.hamcrest.core.Is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileSystemGitRepositoryTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void commitFile() throws Exception {
        final SelfInitRepository fileSystemRepository = new SelfInitRepository(
            new FileSystemGitRepository(folder.getRoot().getPath()));

        fileSystemRepository.commitFile("some_name", "some content");

        assertThat(new File(folder.getRoot().getPath(), "some_name").exists(), Is.is(true));
        assertThat(new TextOf(new File(folder.getRoot().getPath(), "some_name")).asString(), Is.is("some content"));
    }

}