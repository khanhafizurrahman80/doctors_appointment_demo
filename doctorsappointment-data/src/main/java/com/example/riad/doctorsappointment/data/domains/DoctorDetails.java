package com.example.riad.doctorsappointment.data.domains;

import lombok.*;

import javax.persistence.*;

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

    private String day;
    private String time;
    private String contactAddress;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;


    public DoctorDetails(String day, String time, String contactAddress) {
        this.day = day;
        this.time = time;
        this.contactAddress = contactAddress;
    }

}
