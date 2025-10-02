package com.spring.hospitalmanagement.Service;

import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Repository.PatientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository pRepo;


    @Transactional
    public Patient getById(Long id){


        Patient p1 =pRepo.findById(id).orElseThrow();

        Patient p2 =pRepo.findById(id).orElseThrow(); // not even query it because in the persistanse state it checking earlier will the
        //query executed or having the data if find it will use it make p1 as p2 (replaced)

        System.out.println(p1 == p2); // have same reference

        p1.setName("Akib"); // it will change the database even not i saved it because it check is anything
        //changed in the transaction if yes it will auto generate a update query

        return p1;


    }

    @Transactional
    public void delete(Long patientId){

        pRepo.deleteById(patientId);




    }

    @Transactional
    public List<Patient> getAllPatient(){

        return pRepo.findAll();

    }



}
