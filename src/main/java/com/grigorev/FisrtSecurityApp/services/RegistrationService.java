package com.grigorev.FisrtSecurityApp.services;

import com.grigorev.FisrtSecurityApp.models.Person;
import com.grigorev.FisrtSecurityApp.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person) {
        peopleRepository.save(person);
    }
}

