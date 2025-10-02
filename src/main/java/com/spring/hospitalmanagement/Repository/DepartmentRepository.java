package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}