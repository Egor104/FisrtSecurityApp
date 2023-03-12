package com.grigorev.FisrtSecurityApp.config;

// import com.grigorev.FisrtSecurityApp.security.AuthProviderImpl;
import com.grigorev.FisrtSecurityApp.services.PersonDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    // Настраивает аутентификацию. С помощью personDetailsService Spring сам получит человека,
    // сам проверит его логин и пароль
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService); //TODO
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http// Отключаем защиту от межсайтовой подделки запросов, что позволит отправить форму без csrf токена
                // Настраиваем правила авторизации
                .authorizeRequests()
                // Указываем страницы, которые будут доступны всем
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                // Для всех остальных запросов пользователь должен быть аутентифицмрован
                .anyRequest().authenticated()
                // Переходим к настройке правил аутентификации
                .and()
                // Указываем страницу для аутентификации
                .formLogin().loginPage("/auth/login")
                // Указываем страницу, на которую будут отправляться данные из формы (адрес прописан также в login.html)
                .loginProcessingUrl("/process_login")
                // Указываем страницу, на которую будет перенаправлен пользователь после успешной аутентификации
                .defaultSuccessUrl("/hello", true)
                // Указываем страницу, на которую будет перенаправлен пользователь после неудачной аутентификации
                .failureUrl("/auth/login?error")
                // Переходим к настройке логаута
                .and()
                .logout()
                // Задаём URL, при переходе по которому будет происходить логаут
                .logoutUrl("/logout")
                // Указываем страницу, на которую будет перенаправлен пользователь после успешного логаута
                .logoutSuccessUrl("/auth/login");
    }

    // Метод для шифрования пароля (пока не шифруется)
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /* Не нужно, т.к. аутентификация стандартная, AuthProvider убрали
    // Внедряем AuthProvider
    private final AuthProviderImpl authProvider;

    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

        // Настраивает аутентификацию, передаём authProvider, давая понять SpringSecurity,
    // что мы используем именно его для аутентификации пользователей
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider); //TODO
    }
     */


}
