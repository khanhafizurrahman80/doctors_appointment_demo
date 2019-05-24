package com.example.riad.doctorsappointment.web.bootstrap;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.domains.DoctorDetails;
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
        Doctor ind_doctor = doctorRepository.findByFirstName("Mashiur");
        if (ind_doctor == null) {
            doctorRepository.save(getDoctor());
        }
        log.debug("Loading Bootstrap data");
    }

    private Doctor getDoctor() {
        Doctor doctor = new Doctor();
        doctor.setCategory("medicine");
        doctor.setFirstName("Mashiur");
        doctor.setLastName("Rahman");
        doctor.setEmailAddress("maruf@gmail.com");
        doctor.addDoctorDetails(new DoctorDetails("sat", "10 am to 2 pm", "USA" ));
        doctor.addDoctorDetails(new DoctorDetails("sun", "12 am to 8 pm", "USA" ));
        return doctor;
    }


}
