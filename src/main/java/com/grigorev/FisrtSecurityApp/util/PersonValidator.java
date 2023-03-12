package com.grigorev.FisrtSecurityApp.util;

import com.grigorev.FisrtSecurityApp.models.Person;
import com.grigorev.FisrtSecurityApp.services.PersonDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    // Внедрим PDS, т.к. при валидации будем опираться на его метод loadUserByUsername
    // (если исключение выбросится, то всё ок, юзера в базе нет и валидация прошла успешно)
    private final PersonDetailsService personDetailsService;

    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Говорим, что валидатор нужен для объектов класса Person
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // всё ок, пользователь не найден, хотя по хорошему надо это делать через Optional
        }

        errors.rejectValue("username", "", "Такой пользователь уже зарегистрирован");
    }
}
