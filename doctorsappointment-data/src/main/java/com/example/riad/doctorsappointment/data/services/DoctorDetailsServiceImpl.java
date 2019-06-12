package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.repos.DoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorDetailsServiceImpl implements com.example.riad.doctorsappointment.data.services.Interfaces.DoctorDetailsServiceImpl {

    private DoctorDetailsRepository doctorDetailsRepository;

    @Autowired
    public DoctorDetailsServiceImpl(DoctorDetailsRepository doctorDetailsRepository) {
        this.doctorDetailsRepository = doctorDetailsRepository;
    }

    @Override
    public long countDoctorDetails(){
        return doctorDetailsRepository.count();
    }

}
