package com.example.riad.doctorsappointment.data.domains;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void dependentAsseritons(){

        DoctorDetails doctorDetails1 = new DoctorDetails("M1", "90-1", "A1");
        DoctorDetails doctorDetails2 = new DoctorDetails("M2", "90-2", "A2");
        Set<DoctorDetails> doctorDetailsSet = new HashSet<>();
        doctorDetailsSet.add(doctorDetails1);
        doctorDetailsSet.add(doctorDetails2);

        Doctor doctor = new Doctor("fa","la","ea", "pa", "ca" );
        doctor.setDoctorDetails(doctorDetailsSet);


        assertAll("Properties Test",
                () -> assertAll("Doctor property test",
                        () -> assertEquals("fa", doctor.getFirstName()),
                        () -> assertEquals("la", doctor.getLastName()),
                        () -> assertEquals("ea", doctor.getEmailAddress()),
                        () -> assertEquals("pa", doctor.getPhoneNumber()),
                        () -> assertEquals("ca", doctor.getCategory()),
                () -> assertAll("Doctor Details Property Test",
                        () -> assertEquals(doctorDetailsSet.size(), doctor.getDoctorDetails().size()),
                        () -> assertNotNull(doctor.getDoctorDetails()),
                        () -> assertEquals(true, doctorDetailsSet.equals(doctor.getDoctorDetails()))
                )));

    }

}