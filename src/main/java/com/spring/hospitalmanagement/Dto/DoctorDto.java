package com.spring.hospitalmanagement.Dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.spring.hospitalmanagement.Entity.Doctor}
 */
@Value
public class DoctorDto implements Serializable {
    String name;
    String specialization;
    String email;
}