package com.spring.hospitalmanagement.Security;

import com.spring.hospitalmanagement.Dto.LoginRequestDto;
import com.spring.hospitalmanagement.Dto.LoginResponseDto;
import com.spring.hospitalmanagement.Dto.SignupResponseDto;
import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Enum.AuthProviderType;
import com.spring.hospitalmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User signup(LoginRequestDto signupRequest,AuthProviderType authProviderType,String providerId){

        User user = userRepository.findByUsername(signupRequest.getUsername()).orElse(null);


        if(user != null){
            throw new IllegalArgumentException("user Already Exists");
        }
        user = User.builder()
                .username(signupRequest.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build();

        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        }

        return userRepository.save(user);

    }


    public SignupResponseDto sign(LoginRequestDto signupRequest) {



        User user = signup(signupRequest,AuthProviderType.EMAIL,null);


        SignupResponseDto s = new SignupResponseDto();

        mapper.map(user, s);

        return s;



    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        // fetch provider type and provider id
        //save the providertype and provider id info with user
        // if the user has an account: directly login
        //otherwise signup then login


        AuthProviderType providerType = authUtil.getProviderTypeFromRegistraitonId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);


        String email = oAuth2User.getAttribute("email");


        User emailUser = userRepository.findByUsername(email).orElse(null);

        if(user == null && emailUser == null){
            //signup first

            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
            user= signup(new LoginRequestDto(username,null),providerType,providerId);

        }else if(user != null){

            if(email != null && !email.isBlank() && !email.equals(user.getUsername())){

                user.setUsername(email);
                userRepository.save(user);

            }


        }else {

            throw new BadCredentialsException("This Email is Already Registered "+emailUser.getProviderType());

        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.genereateToken(user),user.getId());

        return ResponseEntity.ok(loginResponseDto);

    }
}
