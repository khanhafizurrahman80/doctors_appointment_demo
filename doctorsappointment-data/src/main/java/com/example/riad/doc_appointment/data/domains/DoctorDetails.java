package com.example.riad.doc_appointment.data.domains;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String day;
    private String time;
    private String contactAddress;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

    public DoctorDetails(String day, String time, String contactAddress, Doctor doctor) {
        this.day = day;
        this.time = time;
        this.contactAddress = contactAddress;
        this.doctor = doctor;
    }
}
