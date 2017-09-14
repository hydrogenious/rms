package com.hydrogenious.rms;

import com.hydrogenious.rms.referenceterms.ReferenceTermsRepository;
import com.hydrogenious.rms.referenceterms.impl.DbReferenceTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class DummyInitializer {
    private final ReferenceTermsRepository termsRepository;

    @Autowired
    public DummyInitializer(ReferenceTermsRepository termsRepository) {
        this.termsRepository = termsRepository;
    }

    @PostConstruct
    public void init() {
        termsRepository.save(new DbReferenceTerm());
        termsRepository.save(new DbReferenceTerm());
        termsRepository.save(new DbReferenceTerm());
    }
}
