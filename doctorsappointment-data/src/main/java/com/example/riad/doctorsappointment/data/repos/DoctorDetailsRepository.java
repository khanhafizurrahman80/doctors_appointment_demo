package com.example.riad.doctorsappointment.data.repos;

import com.example.riad.doctorsappointment.data.domains.DoctorDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDetailsRepository extends CrudRepository<DoctorDetails, Long> {

}
