package com.example.riad.doc_appointment.data.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"Doctor"})
@Entity
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;
    private String time;
    private String contactAddress;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

    public DoctorDetails() {
    }

    public DoctorDetails(String day, String time, String contactAddress) {
        this.day = day;
        this.time = time;
        this.contactAddress = contactAddress;
    }

    public DoctorDetails(String day, String time, String contactAddress, Doctor doctor) {
        this.day = day;
        this.time = time;
        this.contactAddress = contactAddress;
        this.doctor = doctor;
    }
}
