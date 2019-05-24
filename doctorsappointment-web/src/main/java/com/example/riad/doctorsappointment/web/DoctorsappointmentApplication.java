package com.example.riad.doctorsappointment.web;


import com.example.riad.doctorsappointment.data.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(DataSourceConfig.class) // very much imp otherwise you will receive an error bean class is not found
public class DoctorsappointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorsappointmentApplication.class, args);
    }

}
