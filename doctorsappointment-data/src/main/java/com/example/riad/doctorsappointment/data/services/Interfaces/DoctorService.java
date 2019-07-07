package com.example.riad.doctorsappointment.data.services.Interfaces;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.domains.DoctorShortDescription;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();

    Doctor addDoctor(Doctor doctor);

    long countDoctors();

    String deleteByFirstName(String firstName);

    String getFirstName();

    List<DoctorShortDescription> getShortDesc();

    Optional<Doctor> getIndividualDesc(Long id);

    Doctor getDoctor(Long id);

    Doctor deleteById(Long id);
}
