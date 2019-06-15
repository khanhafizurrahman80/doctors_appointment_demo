package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.services.AppointmentServiceImpl;
import com.example.riad.doctorsappointment.data.services.Interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/appointment-controller")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping("/get-all-appointments")
    @ResponseBody
    private List<AppointmentBook> getAllAppointments () {
        return this.appointmentService.getAllAppointments();
    }

    @RequestMapping("/add-appointment")
    @ResponseBody
    private ResponseEntity<String> addAppointment(@RequestBody AppointmentBook appointmentBook) throws URISyntaxException {
        this.appointmentService.addAppointment(appointmentBook);
        return  new ResponseEntity<String>("Appointment is booked. Waiting for confirmation", HttpStatus.OK);
    }
}
