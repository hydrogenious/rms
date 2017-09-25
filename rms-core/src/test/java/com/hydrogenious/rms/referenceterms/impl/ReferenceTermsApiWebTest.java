package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.git.impl.FileSystemGitRepositories;
import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import com.hydrogenious.rms.requirement.Requirement;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.BaseRepositoryBuilder;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryCache;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;


public class ReferenceTermsApiWebTest {

    public static final String REQUIREMENT_FILE_CONTENT = "Requirement 1";

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private ReferenceTermsRepository referenceTermsRepository;

    @Before
    public void setUp() throws IOException, GitAPIException {
        referenceTermsRepository = new GitReferenceTermsRepository(
                new FileSystemGitRepositories(folder.getRoot().getPath()));

        File gitRepo = folder.newFolder("git-repo");
        folder.newFolder("not-git-repo");

        Git.init().setDirectory(gitRepo).setBare(false).call();
        Repository repository = new BaseRepositoryBuilder().setWorkTree(gitRepo).build();
        RepositoryCache.register(repository);

        Git git = new Git(repository);

        folder.newFolder("git-repo", "requirements");
        File requirementFile = folder.newFile("git-repo/requirements/1.document");
        try (FileWriter fileWriter = new FileWriter(requirementFile)) {
            fileWriter.write(REQUIREMENT_FILE_CONTENT);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        git.add().addFilepattern("requirements/1.document").call();
        git.commit().setMessage("yep").call();
    }

    @Test
    public void findAllReferenceTerms() {
        final ReferenceTermsApiWeb referenceTermsApi = new ReferenceTermsApiWeb(referenceTermsRepository);
        final Set<? extends ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();
        Assert.assertThat(allReferenceTerms, Matchers.is(Matchers.iterableWithSize(1)));
    }

    @Test
    public void findAllReferenceTermsAndRequirements() throws Exception {
        final ReferenceTermsApiWeb referenceTermsApi = new ReferenceTermsApiWeb(referenceTermsRepository);
        final Set<? extends ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();

        Set<Requirement> allRequirements =
                allReferenceTerms.stream().flatMap(it -> it.getRequirements().stream())
                        .collect(Collectors.toSet());
        Assert.assertThat(allRequirements, Matchers.is(Matchers.iterableWithSize(1)));
        Assert.assertArrayEquals(allRequirements.iterator().next().toByteArray(), REQUIREMENT_FILE_CONTENT.getBytes());
    }
}
