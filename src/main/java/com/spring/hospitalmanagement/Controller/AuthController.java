package com.spring.hospitalmanagement.Controller;

import com.spring.hospitalmanagement.Dto.LoginRequestDto;
import com.spring.hospitalmanagement.Dto.LoginResponseDto;
import com.spring.hospitalmanagement.Dto.SignupRequestDto;
import com.spring.hospitalmanagement.Dto.SignupResponseDto;
import com.spring.hospitalmanagement.Security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){



        return ResponseEntity.ok(authService.login(loginRequestDto));


    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto signupRequest){



        return ResponseEntity.ok(authService.sign(signupRequest));


    }





}
