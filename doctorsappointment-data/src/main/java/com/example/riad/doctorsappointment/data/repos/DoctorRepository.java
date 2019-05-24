package com.example.riad.doctorsappointment.data.repos;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Doctor findByFirstName(String firstName);
    String deleteByFirstName(String firstName);
}
