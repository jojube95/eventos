package com.example.eventos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static com.example.eventos.config.Constants.ROLE_ADMIN;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain basicAuthSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .antMatcher("/**")
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/webjars/**", "/libraries/**", "/favicon.ico").permitAll()
            .antMatchers("/login**", "/error**").permitAll()
            .antMatchers("/eventoAnyadir").hasRole(ROLE_ADMIN)
            .antMatchers("/eventoUpdate").hasRole(ROLE_ADMIN)
            .antMatchers("/eliminarEvento").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/updatePersonas").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/mesas/add").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/mesas/delete").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/mesas/update").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/mesas/invitados/addUpdate").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/mesas/invitados/delete").hasRole(ROLE_ADMIN)
            .antMatchers("/empleados").hasRole(ROLE_ADMIN)
            .antMatchers("/empleadoAnyadir").hasRole(ROLE_ADMIN)
            .antMatchers("/empleadoUpdate").hasRole(ROLE_ADMIN)
            .antMatchers("/empleadoAnyadirUpdate").hasRole(ROLE_ADMIN)
            .antMatchers("/empleadoHistorial").hasRole(ROLE_ADMIN)
            .antMatchers("/eliminarEmpleado").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/empleados").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/empleados/modificar").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/empleados/anyadir").hasRole(ROLE_ADMIN)
            .antMatchers("/evento/empleados/eliminar").hasRole(ROLE_ADMIN)
            .antMatchers("/graficoBarras").hasRole(ROLE_ADMIN)
            .antMatchers("/graficoPastel").hasRole(ROLE_ADMIN)
            .antMatchers("/graficoDispersion").hasRole(ROLE_ADMIN)
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/calendario", true)
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
        return http.build();
    }
}
