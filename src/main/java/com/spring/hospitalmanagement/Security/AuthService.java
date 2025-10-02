package com.spring.hospitalmanagement.Security;

import com.spring.hospitalmanagement.Dto.LoginRequestDto;
import com.spring.hospitalmanagement.Dto.LoginResponseDto;
import com.spring.hospitalmanagement.Dto.SignupResponseDto;
import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager ;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword()));


        User user = (User) authentication.getPrincipal();

        System.out.println(user);

        String token = authUtil.genereateToken(user);

        System.out.println(token);

        return new LoginResponseDto(token,user.getId());

    }

    public SignupResponseDto sign(LoginRequestDto signupRequest) {

        User user = userRepository.findByUsername(signupRequest.getUsername()).orElse(null);


        if(user != null){
         throw new IllegalArgumentException("user Already Exists");
        }

        user = userRepository.save(User.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .build()
        );

        SignupResponseDto s = new SignupResponseDto();

        mapper.map(user, s);

        return s;



    }
}
