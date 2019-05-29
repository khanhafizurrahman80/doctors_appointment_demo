package com.example.riad.doctorsappointment.data.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppointmentBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String appointmentTakenBy;
    private String appointmentTakenTo;
    private String appointmentAddress;
    private String appointmentTime;
    private boolean apprioved;
}