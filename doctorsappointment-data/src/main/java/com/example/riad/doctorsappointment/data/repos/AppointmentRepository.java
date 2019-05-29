package com.example.riad.doctorsappointment.data.repos;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentBook, Long> {

}
