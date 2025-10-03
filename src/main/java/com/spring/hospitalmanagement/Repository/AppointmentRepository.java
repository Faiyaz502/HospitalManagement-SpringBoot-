package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentsByDoctor_Id(Long doctorId);

}