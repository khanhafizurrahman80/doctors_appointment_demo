package com.example.riad.doctorsappointment.data.domains;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppointmentBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "can't be empty")
    @NotNull
    @Size(min = 2, message = "appointtakenby should have atleast 2 characters")
    private String appointmentTakenBy;

    private String appointmentTakenTo;
    private String appointmentAddress;
    private String appointmentTime;
    private boolean approved;
}
