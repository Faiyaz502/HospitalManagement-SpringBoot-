package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}