package com.mycompany.application2.security;

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

    public static final String addUser = "add-user";
    public static final String deleteUser = "delete-user";
    public static final String updateUser = "update-user";
    public static final String viewUser = "view-user";
    public static final String tenentEmployee = "tenentEmployee";
    public static final String tenentAdmin = "tenentAdmin";
    public static final String platformAdmin = "platformAdmin";
    public static final String platformEmployee = "platformEmployee";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configure(http));
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/addTenent-employee").hasAuthority(addUser)
                .requestMatchers("/deleteEmployee").hasAuthority(deleteUser)
                .requestMatchers("/updateEmployee").hasAuthority(updateUser)
                .requestMatchers("/getEmployee").hasAuthority(viewUser)
                .requestMatchers("/tenentEmployee/**").hasAuthority(tenentEmployee)
                .requestMatchers("/tenentAdmin/**").hasAuthority(tenentAdmin)
                .requestMatchers("/platformAdmin/**").hasAuthority(platformAdmin)
                .requestMatchers("/platformEmployee/**").hasAuthority(platformEmployee)
                .anyRequest().authenticated()
        );
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
