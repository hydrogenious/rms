package com.hydrogenious.rms;

import org.eclipse.jgit.util.FS;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class GitFileSystemFactory extends AbstractFactoryBean<FS> {
    @Override
    public Class<?> getObjectType() {
        return FS.class;
    }

    @Override
    protected FS createInstance() throws Exception {
        return FS.DETECTED;
    }
};
