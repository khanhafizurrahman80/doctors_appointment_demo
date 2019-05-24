package com.example.riad.doctorsappointment.web.bootstrap;

import com.example.riad.doctorsappointment.data.repos.DoctorDetailsRepository;
import com.example.riad.doctorsappointment.data.repos.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DoctorAppointmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final DoctorRepository doctorRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;

    public DoctorAppointmentBootstrap(DoctorRepository doctorRepository, DoctorDetailsRepository doctorDetailsRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorDetailsRepository = doctorDetailsRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }
}
