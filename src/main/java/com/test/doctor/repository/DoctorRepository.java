package com.test.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.doctor.Doctors.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

}
