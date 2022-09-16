package com.test.doctor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.doctor.Doctors.Doctor;
import com.test.doctor.exception.DetailsNotFoundException;
import com.test.doctor.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	public DoctorRepository docRepo;
	
	
	
	public List<Doctor> getAllDoctors()
	{
		List<Doctor> docs=new ArrayList<>();
		docRepo.findAll().forEach(docs::add);
		Collections.sort(docs);
		return  docs;
	}
	
	public void addDoctor(Doctor doctor)
    {
		Doctor existingDoctor = docRepo.findById(doctor.getId()).orElse(null);
        if (existingDoctor == null)
        {
        	docRepo.save(doctor);
           
        }
        else
            throw new DetailsNotFoundException("ID already exists in Database!!");
                
    }
	
	public List<Doctor> findDoctorWithSalary(String salary)
	 {
		try
		{
			
			return docRepo.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC,salary));
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public void UpdateDoctor(Doctor doctor)
    {
        Doctor existingDoctor = docRepo.findById(doctor.getId()).orElse(null);
        if (existingDoctor == null)
            throw new DetailsNotFoundException( "No Such ID exists!!");
               
        else
         {
           existingDoctor.setName(doctor.getName());
           existingDoctor.setSalary(doctor.getSalary());
           existingDoctor.setSpecialist(doctor.getSpecialist());
           docRepo.save(existingDoctor);
            
        }
    }
	

}
