package com.spring.hospitalmanagement.Service;

import com.spring.hospitalmanagement.Dto.DoctorRequestDto;
import com.spring.hospitalmanagement.Dto.DoctorResponseDto;
import com.spring.hospitalmanagement.Entity.Doctor;
import com.spring.hospitalmanagement.Entity.Patient;
import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Enum.RoleType;
import com.spring.hospitalmanagement.Repository.DoctorRepository;
import com.spring.hospitalmanagement.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {


        private final DoctorRepository doctorRepository;

        private final UserRepository userRepository;
        private final ModelMapper modelMapper;




    @Transactional
    public List<Doctor> getAllDoctor(){

        return doctorRepository.findAll();

    }

    @Transactional

    public DoctorResponseDto createNewDoctor(DoctorRequestDto doctorRequestDto) {

            User user = userRepository.findById(doctorRequestDto.getUserId()).orElseThrow();

            if(doctorRepository.existsById(doctorRequestDto.getUserId())) {
                throw new IllegalArgumentException("Already a doctor");
            }

            Doctor doctor = Doctor.builder()
                    .name(doctorRequestDto.getName())
                    .specialization(doctorRequestDto.getSpecialization())
                    .user(user)
                    .build();

            user.getRoles().add(RoleType.DOCTOR);

            return modelMapper.map(doctorRepository.save(doctor), DoctorResponseDto.class);
        }


    }
