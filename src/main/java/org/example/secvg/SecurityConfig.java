package org.example.secvg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConditionalOnWebApplication
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/error**").permitAll()  // Allow these paths without authentication
                        .anyRequest().authenticated()  // Require authentication for all other paths, including index.html
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/index.html", true)  // Redirect to index.html after successful login
                );

        return http.build();
    }
}
