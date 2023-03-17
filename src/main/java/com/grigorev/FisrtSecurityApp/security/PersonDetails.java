package com.grigorev.FisrtSecurityApp.security;

import com.grigorev.FisrtSecurityApp.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    // Метод должен возвращать роль пользователя или список его действий в виде коллекции
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    // Аккаунт не просрочен?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Аккаунт не заблокирован?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Пароль не просрочен?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Аккаунт включен?
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно чтобы получать данные аутентифицированного пользователя
    public Person getPerson() {
        return this.person;
    }
}
