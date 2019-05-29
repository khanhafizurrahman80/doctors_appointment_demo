package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.repos.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<AppointmentBook> getAllAppointments() {
        List<AppointmentBook> appointmentBook = new ArrayList<>();
        this.appointmentRepository.findAll().forEach(appointmentBook :: add);
        return appointmentBook;
    }

    public void addAppointment(AppointmentBook appointmentBook) {
        this.appointmentRepository.save(appointmentBook);
    }
}
