package com.jhippolyte.bankaccount.security;

import com.jhippolyte.bankaccount.model.Person;
import com.jhippolyte.bankaccount.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";

    @Autowired
    PersonService personService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authBuilder = auth.inMemoryAuthentication();
        List<Person> personList =  personService.getAllPersons();
        personList.stream().forEach(p -> authBuilder.withUser(p.getMail())
                                                    .password(p.getPassword())
                                                    .roles(p.getRole().toString()));
        ;
    }
    @Override
    protected void configure (HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/bank/accounts").hasRole(ADMIN)
                .antMatchers("/bank/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        http.headers().frameOptions().disable();
    }
}
