package com.spring.hospitalmanagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.spring.hospitalmanagement.Config.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance extends BaseEntity {

    @Column(nullable = false , unique = true , length = 50)
    private String policyNumber;

    @Column(nullable = false , length = 100)
    private String provider ;

    @Column(nullable = false )
    private LocalDate validUntil;

    @JsonIgnore
    @OneToOne(mappedBy = "insurance")  //Inverse Side
    private Patient patient;




}
