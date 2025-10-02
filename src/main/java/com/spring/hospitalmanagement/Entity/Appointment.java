package com.spring.hospitalmanagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.hospitalmanagement.Config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime appointmentTime;


    @Column(length = 500)
    private String reason;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id" ,nullable = false)
    private Patient patient ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Doctor doctor;


}
