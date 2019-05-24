package com.example.riad.doc_appointment.data.repos;

import com.example.riad.doc_appointment.data.domains.DoctorDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDetailsRepository extends CrudRepository<DoctorDetails, Long> {

}
