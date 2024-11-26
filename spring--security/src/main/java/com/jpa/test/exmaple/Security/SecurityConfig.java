package com.jpa.test.exmaple.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.authorizeHttpRequests(registry->
        {
            registry.requestMatchers("/home").permitAll();
            registry.requestMatchers("/user/**").hasRole("USER");
            registry.requestMatchers("/admin/**").hasRole("ADMIN");
            registry.anyRequest().authenticated();
        })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user= User.builder()
                .username("user")
                .password("$2a$12$2VlPI9VeXsJ8xIimqmYiiegUzvl8JQ2LEg23VW6trcXPYYyNynkuG")
                        .roles("USER")
                        .build();
          UserDetails admin= User.builder()
                  .username("admin")
                  .password("$2a$12$rZIXwl6zLXdKGYU5mYXXyeTPm.h0RYEO8nVTTwssVparBWQ8Vyk7e")
                  .roles("ADMIN","USER")
                  .build();
          return new InMemoryUserDetailsManager(user, admin);

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
