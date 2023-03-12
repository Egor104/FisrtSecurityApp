/* package com.grigorev.FisrtSecurityApp.security;

import com.grigorev.FisrtSecurityApp.services.PersonDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

// Нужен только для кастомной аутентификации, для стандартной можно просто реализовать UserDetailsService
// и передать его в SecurityConfig
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    // Внедряем сервис с помощью конструктора
    private final PersonDetailsService personDetailsService;

    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // В этот метод передаются введённые данные из формы, в нём описывается логика аутентификации пользователя.
    // В параметре лежит Credentials (логин и пароль пользователя), а на выходе получаем Principal (объект пользователя).
    // Возвращённый объект будет помещён в сессию, а при каждом запросе пользователя он будет оттудв извлекаться и
    // мы будем иметь доступ к PersonDetails
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Получаем введённое имя пользователя из формы
        String username = authentication.getName();

        // Загружаем User'a с помощью сервиса через username, который приходит из формы
        UserDetails personDetales = personDetailsService.loadUserByUsername(username);

        // Получем введённый пароль из формы
        String password = authentication.getCredentials().toString();

        // Сравниваем полученный пароль с паролем, котоый есть в PersonDetails, то есть настоящим паролем.
        // Если не совпадают - выбрасываем исключение
        if (!password.equals(personDetales.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

//        return new UsernamePasswordAuthenticationToken(
//                personDetales,
//                password,
//                Collections.emptyList());
//    }

    // Чисто технический метод, нужен Spring, чтобы понять для какого объекта Authentication
    // работает конкретный AuthProvider. Т.к. у нас он только один, то просто возвращаем true
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
*/