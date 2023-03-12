package com.grigorev.FisrtSecurityApp.security;

import com.grigorev.FisrtSecurityApp.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
