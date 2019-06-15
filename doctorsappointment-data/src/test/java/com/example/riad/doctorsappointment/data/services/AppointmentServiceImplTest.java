package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.repos.AppointmentRepository;
import com.example.riad.doctorsappointment.data.services.Interfaces.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Test
    public void getAllAppointments() {
        appointmentService.getAllAppointments();
    }

    @Test
    public void addAppointment() {
    }
}
