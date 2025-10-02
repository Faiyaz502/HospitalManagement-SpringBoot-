package com.spring.hospitalmanagement.Dto;

import com.spring.hospitalmanagement.Enum.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupTypeCount {


    private BloodGroup bloodGroup ;

    private Long count ;

}
