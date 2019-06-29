package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.services.AppointmentServiceImpl;
import com.example.riad.doctorsappointment.data.services.Interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private List<AppointmentBook> getAllAppointments () {
        return this.appointmentService.getAllAppointments();
    }

    @RequestMapping(path = "/appointment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    private ResponseEntity<AppointmentBook> addAppointment(@RequestBody AppointmentBook appointmentBook, UriComponentsBuilder uriComponentsBuilder) {
        this.appointmentService.addAppointment(appointmentBook);
        UriComponents uriComponents = uriComponentsBuilder.path("/appointments/appointment/{id}").buildAndExpand(appointmentBook.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(appointmentBook);
    }
}
