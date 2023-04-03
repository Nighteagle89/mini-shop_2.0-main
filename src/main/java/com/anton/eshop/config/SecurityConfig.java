package com.anton.eshop.config;

import com.anton.eshop.data.Role;
import com.anton.eshop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.Basic;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //Внутренне поставщик аутентификации несет ответственность за выполнение процесса аутентификации.
    }

    @Basic
    private AuthenticationProvider authenticationProvider() {
        //Мы хотим, чтобы DaoAuthenticationProvider использовал нашу службу
        // customer UserDetailsService для получения информации о пользователе.
        // Это делается путем расширения WebSecurityConfigurerAdapter.
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //КТО ИМЕЕТ ДОСТУП К ЭТИМ СЫЛЛКАМ
                .antMatchers("/users").hasAnyAuthority(Role.ADMIN.name(), Role.MANAGER.name())
                .antMatchers("/users/create/new").hasAuthority(Role.ADMIN.name())
                //ОСТОЛЬНЫЕ ИМЕЮТ ДОСТУП ВЕЗДЕ
                .anyRequest().permitAll()
                .and()
                //СТРАНИЦА АВТОРИЗАЦИИ
                .formLogin()
                .loginPage("/login")
                //ЕСЛИ АВТОРИЗАЦИЯ НЕ ПРОШЛА ПЕРЕХОДИМ НА ЭТУ ССЫЛКУ
                .failureUrl("/login-error")
                .loginProcessingUrl("/auth")
                .permitAll()
                .and()
                //ВЫХОД С УЧЁТКИ ПО СЫЛЛКЕ
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                //ЗАЩИТА КЛИЕНТ-БРАУЗЕРА
                .csrf().disable();

    }

}