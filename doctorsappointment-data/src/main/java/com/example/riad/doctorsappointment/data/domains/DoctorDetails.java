package com.example.riad.doctorsappointment.data.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"Doctor"})

public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "day must have atleast 2 characters")
    private String day;

    @NotNull
    @Size(min = 2, message = "appointment time must have atleast 2 characters")
    private String appointmentTime;

    @NotNull
    @Size(min = 2, message = "contact address must have atleast 2 characters")
    private String contactAddress;

    @JsonBackReference
    @ManyToOne
    @JoinColumn
    private Doctor doctor;


    public DoctorDetails(String day, String appointmentTime, String contactAddress) {
        this.day = day;
        this.appointmentTime = appointmentTime;
        this.contactAddress = contactAddress;
    }

}
