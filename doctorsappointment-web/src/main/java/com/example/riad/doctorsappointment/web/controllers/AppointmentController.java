package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.data.services.AppointmentServiceImpl;
import com.example.riad.doctorsappointment.data.services.Interfaces.AppointmentService;
import com.example.riad.doctorsappointment.web.config.resources.AppointmentResource;
import com.example.riad.doctorsappointment.web.errors.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private ResponseEntity <Resources<AppointmentResource>> getAllAppointments () {
        final List<AppointmentResource> collection = this.appointmentService.getAllAppointments().stream().map(AppointmentResource::new).collect(Collectors.toList());
        final Resources<AppointmentResource> resources = new Resources<>(collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/appointment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    private ResponseEntity<AppointmentBook> addAppointment(@Valid @RequestBody AppointmentBook appointmentBook, UriComponentsBuilder uriComponentsBuilder) {
        AppointmentBook savedAppointmentBook = this.appointmentService.addAppointment(appointmentBook);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedAppointmentBook.getId()).toUri();
        return ResponseEntity.created(location).body(appointmentBook);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    private ResponseEntity<?> getAppointment(@PathVariable Long id, UriComponentsBuilder uriComponentsBuilder){
        Optional<AppointmentBook> appointment = this.appointmentService.findById(id);
        if (!appointment.isPresent()){
            throw new RecordNotFoundException("Record is not found for id: " + id);
        }else {
            UriComponents uriComponents = uriComponentsBuilder.path("/appointments/appointment/{id}").buildAndExpand(appointment.get().getId());
            return ResponseEntity.ok().body(appointment);
        }
    }

    @RequestMapping(path= "/{id}", method = RequestMethod.PUT,
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<Object> updateAppointment(@Valid @RequestBody AppointmentBook appointmentBook, @PathVariable long id) {
        Optional<AppointmentBook> savedAppointment = this.appointmentService.findById(id);
        if (!savedAppointment.isPresent())
            throw new RecordNotFoundException("Record is not found for id: " + id);
        else{
            AppointmentBook updateAppointment = this.appointmentService.updateAppointment(appointmentBook, id);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(updateAppointment.getId()).toUri();
            return ResponseEntity.created(location).body(updateAppointment);
        }
    }
}
