package com.example.riad.doctorsappointment.data.services.Interfaces;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;

import java.util.List;

public interface AppointmentService {
    List<AppointmentBook> getAllAppointments();

    void addAppointment(AppointmentBook appointmentBook);
}
