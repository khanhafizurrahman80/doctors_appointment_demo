package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.services.DoctorDetailsServiceImpl;
import com.example.riad.doctorsappointment.data.services.DoctorServiceImpl;
import com.example.riad.doctorsappointment.data.services.Interfaces.DoctorService;
import com.example.riad.doctorsappointment.web.errors.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/doctors")
@Slf4j
public class DoctorController {

    private DoctorService doctorService;
    private DoctorDetailsServiceImpl doctorDetailsService;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorDetailsServiceImpl doctorDetailsService) {
        this.doctorService = doctorService;
        this.doctorDetailsService = doctorDetailsService;
    }

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorService.deleteById(id);
        if (doctor == null)
            throw new RecordNotFoundException("Record is not found for id: " + id);
    }
    @RequestMapping(path = "/doctor", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<Object> addDoctor(@Valid @RequestBody Doctor doctor) throws URISyntaxException {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDoctor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<Doctor> getDoctor(@PathVariable Long id){
        Doctor savedDoctor = doctorService.getDoctor(id);

        if (savedDoctor == null)
            throw new RecordNotFoundException("Record is not found for id: " + id);

        Resource<Doctor> resources = new Resource<Doctor>(savedDoctor);
        final Link linkTo = ControllerLinkBuilder.linkTo
                (ControllerLinkBuilder.methodOn(this.getClass()).getAllDoctors()).withRel("all-Doctors");
        resources.add(linkTo);

        return resources;
    }
}
