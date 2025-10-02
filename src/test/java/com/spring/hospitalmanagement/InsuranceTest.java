package com.spring.hospitalmanagement;

import com.spring.hospitalmanagement.Entity.Appointment;
import com.spring.hospitalmanagement.Entity.Insurance;


import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Service.AppointmentService;
import com.spring.hospitalmanagement.Service.InsuranceService;
import com.spring.hospitalmanagement.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest

public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Test
    public void TestPatient() {

        Insurance insurance = Insurance.builder()
                .policyNumber("654651354")
                .provider("AXIM BANK")
                .validUntil(LocalDate.of(2027, 12, 10))
                .build();

        Patient p = insuranceService.assignInsuranceToPatient(insurance, 3L);


        insuranceService.disassociateInsurance(p.getId());


    }

    @Test
    public void TestAppointment() {


        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 2, 1))
                .reason("Cancer")
                .build();

//
        Appointment app = appointmentService.createAppointment(appointment, 1L, 1L);


        /// reassign

//        appointmentService.ReassignAppointment(1L, 2L);


    }

    @Test
    public void Cascade(){

//        Patient patient = patientService.getById(7L);
//
//        System.out.println(patient);
//
//
//        Insurance insurance = Insurance.builder()
//                .policyNumber("654651354")
//                .provider("AXIM BANK")
//                .validUntil(LocalDate.of(2027, 12, 10))
//                .build();


//        insuranceService.assignInsuranceToPatient(insurance,7L);

      patientService.delete(7L);






    }


}
