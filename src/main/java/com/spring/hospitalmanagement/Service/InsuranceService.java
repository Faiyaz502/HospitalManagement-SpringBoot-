package com.spring.hospitalmanagement.Service;

import com.spring.hospitalmanagement.Entity.Insurance;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Repository.InsuranceRepository;
import com.spring.hospitalmanagement.Repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepo;
    private final PatientRepository patientRepo;



    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance,Long patientId){


        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("patient not found Id :"+patientId));


        // Make sure Patient is owning side of OneToOne
        patient.setInsurance(insurance);
        insurance.setPatient(patient); // optional, keeps bidirectional in sync

        // No need to call insuranceRepo.save(), cascade = ALL handles it
        return patient;

    }

    @Transactional
    public void disassociateInsurance(Long patientId){

        Patient patient = patientRepo.findById(patientId).orElseThrow();

        patient.setInsurance(null);


    }

    @Transactional
    public List<Insurance> getAllInsurance(){

        return insuranceRepo.findAll();

    }






}
