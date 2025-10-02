package com.spring.hospitalmanagement.Controller;

import com.spring.hospitalmanagement.Entity.Insurance;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Repository.InsuranceRepository;
import com.spring.hospitalmanagement.Service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {


    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public ResponseEntity<?> getAllinsurance(){

        List<Insurance> p = insuranceService.getAllInsurance();


        return ResponseEntity.ok(p);

    }

}
