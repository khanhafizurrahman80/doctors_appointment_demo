package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.repos.AppointmentRepository;
import com.example.riad.doctorsappointment.data.services.Interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<AppointmentBook> getAllAppointments() {
        List<AppointmentBook> appointmentBook = new ArrayList<>();
        this.appointmentRepository.findAll().forEach(appointmentBook :: add);
        return appointmentBook;
    }

    @Override
    public AppointmentBook addAppointment(AppointmentBook appointmentBook) {
        this.appointmentRepository.save(appointmentBook);
        return appointmentBook;
    }

    @Override
    public Optional<AppointmentBook> findById(Long id) {
        return this.appointmentRepository.findById(id);
    }

    @Override
    public AppointmentBook updateAppointment(AppointmentBook appointmentBook, Long id) {
        AppointmentBook savedAppointmentBook = this.appointmentRepository.findById(id).get();
        savedAppointmentBook.setAppointmentAddress(appointmentBook.getAppointmentAddress());
        savedAppointmentBook.setAppointmentTakenBy(appointmentBook.getAppointmentTakenBy());
        savedAppointmentBook.setAppointmentTakenTo(appointmentBook.getAppointmentTakenTo());
        savedAppointmentBook.setAppointmentTime(appointmentBook.getAppointmentTime());
        savedAppointmentBook.setApproved(appointmentBook.isApproved());
        this.appointmentRepository.save(savedAppointmentBook);
        return savedAppointmentBook;
    }


}
