package com.example.riad.doctorsappointment.data.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 2, message = "First Name must have atleast 2 characters")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last Name must have atleast 2 characters")
    private String lastName;
    @Email(message = "Email address is not valid!")
    private String emailAddress;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;
    @Size(min = 2, message = "category must have atleast 2 characters")
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
