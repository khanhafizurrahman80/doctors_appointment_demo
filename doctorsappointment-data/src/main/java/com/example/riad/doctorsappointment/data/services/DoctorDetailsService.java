package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.repos.DoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorDetailsService {

    private DoctorDetailsRepository doctorDetailsRepository;

    @Autowired
    public DoctorDetailsService(DoctorDetailsRepository doctorDetailsRepository) {
        this.doctorDetailsRepository = doctorDetailsRepository;
    }

    public long countDoctorDetails(){
        return doctorDetailsRepository.count();
    }

}
