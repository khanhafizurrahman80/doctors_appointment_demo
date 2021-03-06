package com.example.riad.doctorsappointment.data.services.Interfaces;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<AppointmentBook> getAllAppointments();

    AppointmentBook addAppointment(AppointmentBook appointmentBook);

    Optional<AppointmentBook> findById(Long id);

    AppointmentBook updateAppointment(AppointmentBook appointmentBook, Long id);
}
