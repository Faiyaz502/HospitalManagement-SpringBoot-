package com.spring.hospitalmanagement;

import com.spring.hospitalmanagement.Dto.BloodGroupTypeCount;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Enum.BloodGroup;
import com.spring.hospitalmanagement.Repository.PatientRepository;
import com.spring.hospitalmanagement.Service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository repo ;

    @Autowired
    private PatientService service;


    @Test
    public void testRepo(){

      List<Patient> p =  repo.findAll();
        System.out.println(p);

    }

    @Test
    public void testTransactionMethod(){
//
//        Patient patient = service.getById(1L);
//
//        System.out.println(patient);

        //-----------------
//
//        Patient patient1 = repo.findByName("Robert Brown");
//
//        System.out.println(patient1);
//
//        List<Patient> p3 = repo.findAllByBirthDateOrEmail(LocalDate.of(1990,03,15),"john.doe@example.com");
//
//
//        System.out.println(p3);
//
//
//        List<Patient> p4 = repo.findByBirthDateBetween(LocalDate.of(1990,03,15),LocalDate.of(2020,01,01));
//
//        System.out.println(p4);
//
//
//        List<Patient> p5 = repo.findByNameContainingIgnoreCase("li");
//
//        System.out.println(p5);
//
//        List<Patient> p6 = repo.findByBloodGroup(BloodGroup.O_POSITIVE);
//
//        System.out.println(p6);
//
//        List<Object[]> count = repo.CountByBloodGroup();
//
//        for (Object[] obj : count) {
//
//            System.out.println(obj[0]+" "+obj[1]);
//        }
//
//        int rowUpdated = repo.updateNameById("Faiyaz" , 3L);
//
//        System.out.println(rowUpdated);
//
//
//        List<BloodGroupTypeCount> bt = repo.CountAllBloodGroupMember();
//
//        System.out.println(bt);


        Page<Patient> patientList = repo.findAllPatient(PageRequest.of(0,3, Sort.by("name")));

        for (Patient p : patientList) {

            System.out.println(p);
        }


    }

    @Test
    public void testPatientWithAppointment(){


        List<Patient> p = repo.findAllPatientWithAppointment();

        System.out.println(p.toString());



    }
}
