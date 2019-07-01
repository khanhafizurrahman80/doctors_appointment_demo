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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

        //given
        AppointmentBook appointmentBook = new AppointmentBook();
        List<AppointmentBook> appointmentBooks = new ArrayList<>();
        appointmentBooks.add(appointmentBook);
        given(appointmentRepository.findAll()).willReturn(appointmentBooks);

        //when
        List<AppointmentBook> foundAppointmentBooks = appointmentService.getAllAppointments();

        //then
        then(appointmentRepository).should().findAll();

        assertThat(foundAppointmentBooks).hasSize(1);
    }

    @Test
    public void addAppointment() {
        //given
        AppointmentBook appointmentBook = new AppointmentBook();
        given(appointmentRepository.save(any(AppointmentBook.class))).willReturn(appointmentBook);

        //when
        AppointmentBook savedAppointmentBook = appointmentService.addAppointment(new AppointmentBook());

        //then
        then(appointmentRepository).should().save(any(AppointmentBook.class));
        assertThat(savedAppointmentBook).isNotNull();
    }


    @Test
    void findById() {
        //given
        AppointmentBook appointmentBook = new AppointmentBook();
        given(appointmentRepository.findById(anyLong())).willReturn(Optional.of(appointmentBook));

        //when
        AppointmentBook savedAppointmentBook = appointmentService.findById(1L).get();

        //then
        then(appointmentRepository).should().findById(anyLong());

        assertThat(savedAppointmentBook).isNotNull();

    }

    @Test
    void updateAppointment() {
    }
}
