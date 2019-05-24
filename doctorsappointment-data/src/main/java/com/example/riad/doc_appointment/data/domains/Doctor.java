package com.example.riad.doc_appointment.data.domains;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String category;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JoinColumn
    private Set<DoctorDetails> doctorDetails;

    public Doctor(String firstName, String lastName, String emailAddress, String phoneNumber, String category, Set<DoctorDetails> doctorDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.doctorDetails = doctorDetails;
    }
}
