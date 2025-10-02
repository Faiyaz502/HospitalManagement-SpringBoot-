package com.spring.hospitalmanagement.Controller;

import com.spring.hospitalmanagement.Entity.Appointment;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {


    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPatient(){

        List<Appointment> p = appointmentService.getAllAppoinment();


        return ResponseEntity.ok(p);

    }

}
