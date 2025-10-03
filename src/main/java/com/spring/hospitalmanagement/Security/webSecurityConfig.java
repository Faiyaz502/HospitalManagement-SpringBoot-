package com.spring.hospitalmanagement.Security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.spring.hospitalmanagement.Enum.RoleType.*;
import static com.spring.hospitalmanagement.Enum.RoleType.DOCTOR;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class webSecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/patient", "/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("/doctor/**").hasAnyRole(DOCTOR.name(), ADMIN.name())
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth ->
                        oauth.failureHandler((
                                (request, response, exception) ->
                                {
                                    log.error("OAuth2 Rooror:{}", exception.getMessage());
                                    handlerExceptionResolver.resolveException(request,response,null,exception);
                                })

                        ).successHandler(oAuth2SuccessHandler)
                ).exceptionHandling(exceptionConfig ->
                        exceptionConfig.accessDeniedHandler((request, response, accessDeniedException) ->

                                        handlerExceptionResolver.resolveException(request,response,null,accessDeniedException)

                                )
                );


        return httpSecurity.build();


    }


}
