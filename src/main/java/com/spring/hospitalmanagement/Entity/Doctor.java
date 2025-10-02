package com.spring.hospitalmanagement.Entity;

import com.spring.hospitalmanagement.Config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor extends BaseEntity {

    @Column(nullable = false,length = 100)
    private String name ;

    @Column(length = 100)
    private String specialization;


    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(mappedBy = "doctors")
    private Set<Department> departments = new HashSet<>();


}
