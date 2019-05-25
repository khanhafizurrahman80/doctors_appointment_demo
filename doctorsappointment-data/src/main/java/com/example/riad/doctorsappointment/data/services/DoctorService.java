package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.domains.DoctorShortDescription;
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

        doctorlist .forEach(doctors::add);

        return doctors;
    }

    public void addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public long countDoctors(){
        return doctorRepository.count();
    }

    public String deleteByFirstName(String firstName) {
        doctorRepository.deleteByFirstName(firstName);
        return "delete succeded";
    }

    public String getFirstName() {
        return "Riad";
    }

    public List<DoctorShortDescription> getFullName() {
        List<Doctor> doctorList = getAllDoctors();
        List<DoctorShortDescription> doctorShortDescriptions = new ArrayList<>();
        doctorList.forEach(doctor -> {
           doctorShortDescriptions.add(new DoctorShortDescription(doctor.getFirstName() + " " +doctor.getLastName(), doctor.getCategory()));
        });
        return doctorShortDescriptions;
    }


}
