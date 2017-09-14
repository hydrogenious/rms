package com.hydrogenious.rms.referenceterms;

import com.hydrogenious.rms.referenceterms.impl.DbReferenceTerm;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ReferenceTermsRepository extends CrudRepository<DbReferenceTerm, Long> {
    Set<DbReferenceTerm> findAll();
}
