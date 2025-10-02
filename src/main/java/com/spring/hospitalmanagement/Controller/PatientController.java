package com.spring.hospitalmanagement.Controller;

import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Repository.PatientRepository;
import com.spring.hospitalmanagement.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/patient")
class PatientController {


    private final PatientService patientService;

    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping
    public ResponseEntity<?> getAllPatient(){

        List<Patient> p = patientService.getAllPatient();


        return ResponseEntity.ok(p);

    }




}
