package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Dto.BloodGroupTypeCount;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Enum.BloodGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {


    //Query Methods

     Patient findByName(String name);

    List<Patient> findAllByBirthDateOrEmail(LocalDate birthDate,String email);

    List<Patient> findByBirthDateBetween(LocalDate birthDateAfter, LocalDate birthDateBefore);

    List<Patient> findByNameContainingIgnoreCase(String name);


    //JPQL
    @Query("Select p from Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfter(@Param("birthDate") LocalDate birthDate);


    @Query("select p.bloodGroup ,count(p) from Patient p group by p.bloodGroup")
    List<Object[]> CountByBloodGroup();

    @Query("select new com.spring.hospitalmanagement.Dto.BloodGroupTypeCount( p.bloodGroup ,count(p))" +
            " from Patient p group by p.bloodGroup")
    List<BloodGroupTypeCount> CountAllBloodGroupMember();


    //Native Query

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatient(Pageable pageable);



    @Transactional
    @Modifying
    @Query("update Patient p set p.name= :name where p.id = :id")
    int updateNameById(@Param("name") String name , @Param("id") Long id);


    @Query("select p from Patient p LEFT join fetch p.appointments ")
    List<Patient> findAllPatientWithAppointment();

}
