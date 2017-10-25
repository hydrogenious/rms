package com.hydrogenious.rms.requirement.impl;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import org.cactoos.text.TextOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RequirementsApiWebTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void save() throws Exception {
        new RequirementsApiWeb(folder.getRoot().getPath()).save(
            "rms",
            new ObjectNode(JsonNodeFactory.instance)
                .put("name", "requirement")
                .put("content", "requirement content")
        );

        final File rmsRepository = new File(folder.getRoot(), "rms");
        Assert.assertThat(rmsRepository.exists(), Is.is(true));
        Assert.assertThat(new File(rmsRepository, ".git").exists(), Is.is(true));
        final File requirement = new File(rmsRepository, "requirement");
        Assert.assertThat(requirement.exists(), Is.is(true));
        Assert.assertThat(new TextOf(requirement).asString(), Is.is("requirement content"));
    }

}