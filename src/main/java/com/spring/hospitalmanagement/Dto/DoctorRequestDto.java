package com.spring.hospitalmanagement.Dto;

import lombok.Data;

@Data
public class DoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
