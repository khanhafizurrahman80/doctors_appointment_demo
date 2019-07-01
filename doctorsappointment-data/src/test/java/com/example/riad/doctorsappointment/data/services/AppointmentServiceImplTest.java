package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.repos.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentServiceImpl appointmentService;

    @Test
    public void getAllAppointments() {
        AppointmentBook appointmentBook = new AppointmentBook();
        List<AppointmentBook> appointmentBooks = new ArrayList<>();

        appointmentBooks.add(appointmentBook);

        when(appointmentRepository.findAll()).thenReturn(appointmentBooks);

        List<AppointmentBook> foundAppointmentBooks = appointmentService.getAllAppointments();

        verify(appointmentRepository).findAll();

        assertThat(foundAppointmentBooks).hasSize(1);
    }

    @Test
    public void addAppointment() {
        AppointmentBook appointmentBook = new AppointmentBook();

        when(appointmentRepository.save(any(AppointmentBook.class))).thenReturn(appointmentBook);

        AppointmentBook savedAppointmentBook = appointmentService.addAppointment(new AppointmentBook());

        verify(appointmentRepository).save(any(AppointmentBook.class));

        assertThat(savedAppointmentBook).isNotNull();
    }


    @Test
    void findById() {
        AppointmentBook appointmentBook = new AppointmentBook();

        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointmentBook));

        AppointmentBook savedAppointmentBook = appointmentService.findById(1L).get();

        verify(appointmentRepository).findById(anyLong());

        assertThat(savedAppointmentBook).isNotNull();

    }

    @Test
    void updateAppointment() {
    }
}
