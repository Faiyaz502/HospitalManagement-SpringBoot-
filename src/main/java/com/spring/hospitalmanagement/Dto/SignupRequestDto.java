package com.spring.hospitalmanagement.Dto;

import com.spring.hospitalmanagement.Enum.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {


    private String username;
    private String password;
    private String name;

    private Set<RoleType> roles = new HashSet<>();


}
