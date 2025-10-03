package com.spring.hospitalmanagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.hospitalmanagement.Config.BaseEntity;
import com.spring.hospitalmanagement.Enum.BloodGroup;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "patient" ,
        uniqueConstraints = {

                @UniqueConstraint(name = "name_birth", columnNames = {"name","birth_date"})

        },
        indexes = {
                @Index(name = "idx_patient_name" , columnList = "name , email")
        }

)
public class Patient extends BaseEntity {

    @Column( nullable = false ,length = 40)
    private String name;

    private LocalDate birthDate;

    @ToString.Exclude
    @Column(unique = true)
    private String email;

    @OneToOne
    @MapsId
    private User user;

    private String gender;


    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")
    private Insurance insurance;


    @JsonIgnoreProperties()
    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY ,orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();



    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this); // ✅ set both sides
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setPatient(null);
    }



}
