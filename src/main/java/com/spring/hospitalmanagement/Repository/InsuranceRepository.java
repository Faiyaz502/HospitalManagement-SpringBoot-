package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}