package com.spring.hospitalmanagement.Config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }







//    @Bean
//    UserDetailsService userDetailsService(){
//
//
//        UserDetails user1 = User.withUsername("ADMIN")
//                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.withUsername("DOCTOR")
//                .password(passwordEncoder.encode("123"))
//                .roles("DOCTOR")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1,user2);
//
//
//    }
}
