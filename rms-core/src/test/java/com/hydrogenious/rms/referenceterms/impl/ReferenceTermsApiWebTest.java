package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.git.impl.FileSystemGitRepositories;
import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.util.FS;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;


public class ReferenceTermsApiWebTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private ReferenceTermsRepository referenceTermsRepository;

    @Before
    public void setUp() throws IOException {
        referenceTermsRepository = new GitReferenceTermsRepository(
                new FileSystemGitRepositories(folder.getRoot().getPath(), FS.DETECTED));

        File gitRepo = folder.newFolder("git-repo");
        folder.newFolder("not-git-repo");

        FileRepository fileRepository = new FileRepository(gitRepo);
        fileRepository.create(true);
        RepositoryCache.register(fileRepository);
    }

    @Test
    public void findAllReferenceTerms() {
        final ReferenceTermsApiWeb referenceTermsApi = new ReferenceTermsApiWeb(referenceTermsRepository);
        final Set<? extends ReferenceTerm> allReferenceTerms = referenceTermsApi.findAllReferenceTerms();
        Assert.assertThat(allReferenceTerms, is(iterableWithSize(1)));
    }
}
