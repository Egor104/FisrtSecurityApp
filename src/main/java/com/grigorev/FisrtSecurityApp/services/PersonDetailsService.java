package com.grigorev.FisrtSecurityApp.services;

import com.grigorev.FisrtSecurityApp.models.Person;
import com.grigorev.FisrtSecurityApp.repositories.PeopleRepository;
import com.grigorev.FisrtSecurityApp.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Чтобы Spring Security знал, что этот сервис загружает пользователя реализуем интерфейс UserDetailsService
// с единственным методом loadUserByUsername(String username)
@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(person.get());
        // Можем вернуть PersonDetails, т.к. этот класс реализует интерфейс UserDetails
    }
}
// Теперь можем использовать этот сервис в AuthenticationProvider в методе authenticate
