package com.example.eventos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login**", "/resources/**", "/webjars/**").permitAll()
                .antMatchers("/anyadirEvento").hasRole("ADMIN")
                .antMatchers("/updateEvento").hasRole("ADMIN")
                .antMatchers("/eliminarEvento").hasRole("ADMIN")
                .antMatchers("/evento/updatePersonas").hasRole("ADMIN")
                .antMatchers("/evento/mesas/add").hasRole("ADMIN")
                .antMatchers("/evento/mesas/delete").hasRole("ADMIN")
                .antMatchers("/evento/mesas/update").hasRole("ADMIN")
                .antMatchers("/evento/mesas/invitados/addUpdate").hasRole("ADMIN")
                .antMatchers("/evento/mesas/invitados/delete").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login"))
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }
}
