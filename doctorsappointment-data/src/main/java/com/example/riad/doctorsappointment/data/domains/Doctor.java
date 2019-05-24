package com.example.riad.doctorsappointment.data.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor {
    // remember the id annotation from persistence not from the spring annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String category;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<DoctorDetails> doctorDetails = new HashSet<>();

    public Doctor(String firstName, String lastName, String emailAddress, String phoneNumber, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.category = category;
    }

    public Set<DoctorDetails> getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(Set<DoctorDetails> doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public Doctor addDoctorDetails(DoctorDetails doctorDetail){
        doctorDetail.setDoctor(this);
        this.doctorDetails.add(doctorDetail);
        return this;
    }
}
