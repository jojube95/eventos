package com.example.eventos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration {
    private static final String ROLE_ADMIN = "ADMIN";
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/anyadirEvento").hasRole(ROLE_ADMIN)
                .antMatchers("/updateEvento").hasRole(ROLE_ADMIN)
                .antMatchers("/eliminarEvento").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/updatePersonas").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/mesas/add").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/mesas/delete").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/mesas/update").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/mesas/invitados/addUpdate").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/mesas/invitados/delete").hasRole(ROLE_ADMIN)
                .antMatchers("/empleados").hasRole(ROLE_ADMIN)
                .antMatchers("/anyadirEmpleado").hasRole(ROLE_ADMIN)
                .antMatchers("/updateEmpleado").hasRole(ROLE_ADMIN)
                .antMatchers("/anyadirUpdateEmpleado").hasRole(ROLE_ADMIN)
                .antMatchers("/historialEmpleado").hasRole(ROLE_ADMIN)
                .antMatchers("/eliminarEmpleado").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/empleados").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/empleados/modificar").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/empleados/anyadir").hasRole(ROLE_ADMIN)
                .antMatchers("/evento/empleados/eliminar").hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/calendario"))
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/webjars/**", "/libraries/**", "/favicon.ico");
    }
}
