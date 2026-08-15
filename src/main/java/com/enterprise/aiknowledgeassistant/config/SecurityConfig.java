package com.enterprise.aiknowledgeassistant.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // OAuth endpoints
                        .requestMatchers(
                                "/oauth2/**",
                                "/login/**"
                        ).permitAll()

                        // Authentication information endpoint
                        .requestMatchers(
                                "/api/v1/auth/me"
                        ).authenticated()

                        // Session APIs - currently public
                        .requestMatchers(
                                "/api/v1/sessions/**"
                        ).permitAll()

                        // Existing APIs
                        .anyRequest().permitAll()
                )

                .oauth2Login(oauth -> oauth
                        .defaultSuccessUrl(
                                "/api/v1/auth/me",
                                true
                        )
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            response.getWriter().write("""
                    {
                        "authenticated": false,
                        "message": "Logout successful"
                    }
                    """);
                        })
                )

                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}