package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.repos.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        Iterable<Doctor> doctorlist = doctorRepository.findAll();
        int count = 0;
        for (Doctor d: doctorlist){
            count +=1;
        }
        log.debug("total count " + count);
        doctorlist .forEach(doctors::add);

        return doctors;
    }

    public void addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public long countDoctors(){
        return doctorRepository.count();
    }
}