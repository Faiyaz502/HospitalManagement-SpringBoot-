package com.spring.hospitalmanagement.Entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.spring.hospitalmanagement.Config.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {

    @Column(nullable = false,length = 100, unique = true)
    private String name ;

    @OneToOne
    private Doctor headDoctor;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();


}
