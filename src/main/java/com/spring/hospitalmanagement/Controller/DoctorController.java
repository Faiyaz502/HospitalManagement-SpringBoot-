package com.spring.hospitalmanagement.Controller;

import com.spring.hospitalmanagement.Dto.DoctorDto;
import com.spring.hospitalmanagement.Dto.DoctorRequestDto;
import com.spring.hospitalmanagement.Dto.DoctorResponseDto;
import com.spring.hospitalmanagement.Entity.Appointment;
import com.spring.hospitalmanagement.Entity.Doctor;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Service.AppointmentService;
import com.spring.hospitalmanagement.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {


    private final DoctorService doctorService;

    private final AppointmentService appointmentService;



    @GetMapping
    public ResponseEntity<?> getAllDoctor(){

        List<Doctor> p = doctorService.getAllDoctor();


        return ResponseEntity.ok(p);

    }

    /// current Doctor Appointment

    @GetMapping("/appointment")
    public ResponseEntity<?> getAllAppointmentofDoctor(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Appointment> p = appointmentService.getAllAppointmentofDoctor(user.getId());


        return ResponseEntity.ok(p);

    }

    @PostMapping("/new_doctor")
    public ResponseEntity<DoctorResponseDto> addNewDoctor(@RequestBody DoctorRequestDto doctorRequestDto){




        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createNewDoctor(doctorRequestDto));
    }
}
