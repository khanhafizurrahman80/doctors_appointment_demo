package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.domains.DoctorShortDescription;
import com.example.riad.doctorsappointment.data.repos.DoctorRepository;
import com.example.riad.doctorsappointment.data.services.Interfaces.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        Iterable<Doctor> doctorlist = doctorRepository.findAll();

        doctorlist .forEach(doctors::add);

        return doctors;
    }

    @Override
    public Doctor addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public long countDoctors(){
        return doctorRepository.count();
    }

    @Override
    public String deleteByFirstName(String firstName) {
        doctorRepository.deleteByFirstName(firstName);
        return "delete succeded";
    }

    @Override
    public String getFirstName() {
        return "Riad";
    }

    @Override
    public List<DoctorShortDescription> getShortDesc() {
        List<Doctor> doctorList = getAllDoctors();
        List<DoctorShortDescription> doctorShortDescriptions = new ArrayList<>();
        doctorList.forEach(doctor -> {
           doctorShortDescriptions.add(new DoctorShortDescription(doctor.getId(),doctor.getFirstName() + " " +doctor.getLastName(), doctor.getCategory()));
        });
        return doctorShortDescriptions;
    }

    @Override
    public Optional<Doctor> getIndividualDesc(Long id) {
        Optional<Doctor> individualDoc = doctorRepository.findById(id);
        return individualDoc;
    }

    @Override
    public Doctor getDoctor(Long id) {
        Optional<Doctor> mayBsavedDoctor = doctorRepository.findById(id);
        Doctor savedDoctor = mayBsavedDoctor.isPresent() ? mayBsavedDoctor.get() : null;
        return savedDoctor;
    }

    @Override
    public Doctor deleteById(Long id) {
        Doctor savedDoctor = getDoctor(id);
        doctorRepository.deleteById(id);
        return savedDoctor;
    }
}
