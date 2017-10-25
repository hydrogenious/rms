package com.hydrogenious.rms.requirement.impl;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GitRequirementTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void update() throws Exception {
        new GitRequirement(
            folder.getRoot().getPath(),
            new ObjectNode(JsonNodeFactory.instance)
                .put("name", "rms")
                .put("content", "rms content")
        ).update();

        Assert.assertThat(new File(folder.getRoot(), "rms").exists(), Is.is(true));
    }

}