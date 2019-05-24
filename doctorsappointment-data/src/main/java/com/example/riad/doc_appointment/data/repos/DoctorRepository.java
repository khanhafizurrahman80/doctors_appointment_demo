package com.example.riad.doc_appointment.data.repos;

import com.example.riad.doc_appointment.data.domains.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

}
