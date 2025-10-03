package com.spring.hospitalmanagement.Service;

import com.spring.hospitalmanagement.Entity.Appointment;
import com.spring.hospitalmanagement.Entity.Doctor;
import com.spring.hospitalmanagement.Entity.Insurance;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Repository.AppointmentRepository;
import com.spring.hospitalmanagement.Repository.DoctorRepository;
import com.spring.hospitalmanagement.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    @Transactional
    @Secured("ROLE_ADMIN")
    public Appointment createAppointment(Appointment appointment,Long doctorID,Long patientId){


        Doctor doctor = doctorRepository.findById(doctorID).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have a ID");


        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

     return   appointmentRepository.save(appointment);


    }


    @Transactional
    public void ReassignAppointment(Long appointID,Long doctorID){

            Appointment app = appointmentRepository.findById(appointID).orElseThrow();

            Doctor doc = doctorRepository.findById(doctorID).orElseThrow();


            app.setDoctor(doc);

//            doc.getAppointments().add(app);



    }
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR') AND #doctorId == authentication.principal.id)")
    @Transactional
    public List<Appointment> getAllAppoinment(){

        return appointmentRepository.findAll();

    }

    public List<Appointment> getAllAppointmentofDoctor(Long id) {


       return appointmentRepository.findAppointmentsByDoctor_Id(id);


    }
}
