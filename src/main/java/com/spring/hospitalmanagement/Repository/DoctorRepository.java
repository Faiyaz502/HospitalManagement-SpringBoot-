package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {



}