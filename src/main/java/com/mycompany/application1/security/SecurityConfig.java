package com.mycompany.application1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String RECRUITER = "recruiter";
    public static final String INTERVIEWER = "interviewers";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configure(http));
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/admin/**").hasAuthority(ADMIN)
                .requestMatchers("/recruiter/**").hasAuthority(RECRUITER)
                .requestMatchers("/interviewers/**").hasAuthority(INTERVIEWER)
                .anyRequest().authenticated()
        );
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("/applicant-service/api/update-draft/{id}"));
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                .jwtAuthenticationConverter(jwtAuthConverter)
                )
        );

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();

    }

}
