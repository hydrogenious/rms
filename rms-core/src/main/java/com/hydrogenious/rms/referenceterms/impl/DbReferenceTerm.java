package com.hydrogenious.rms.referenceterms.impl;

import com.hydrogenious.rms.referenceterms.ReferenceTerm;
import com.hydrogenious.rms.requirement.Requirement;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class DbReferenceTerm implements ReferenceTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Transient
    @Override
    public Set<Requirement> getRequirements() {
        return Collections.emptySet();
    }
}
