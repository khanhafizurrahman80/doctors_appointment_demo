package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.domains.DoctorShortDescription;
import com.example.riad.doctorsappointment.data.services.DoctorDetailsService;
import com.example.riad.doctorsappointment.data.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.attribute.URISyntax;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {

    private DoctorService doctorService;
    private DoctorDetailsService doctorDetailsService;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorDetailsService doctorDetailsService) {
        this.doctorService = doctorService;
        this.doctorDetailsService = doctorDetailsService;
    }

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping("/get-all")
    @ResponseBody
    public List<Doctor> getAllDoctors(){
        int count = doctorService.getAllDoctors().size();
        log.debug("get all doctors " + count);
        return doctorService.getAllDoctors();
    }

    @RequestMapping("/total-doctors")
    @ResponseBody
    public long countAllDoctors() {
        return doctorService.countDoctors();
    }

    @RequestMapping("/total-doctors-details")
    @ResponseBody
    public long countAllDoctorsDetails() {
        return doctorDetailsService.countDoctorDetails();
    }

    @RequestMapping("/delete-by-id/{firstName}")
    @ResponseBody
    @Transactional
    public String deleteByFirstName(@PathVariable String firstName){
        doctorService.deleteByFirstName(firstName);
        return "is deleted!!!";
    }

    @RequestMapping("/get-first-name")
    @ResponseBody
    public String getFirstName() {
        return doctorService.getFirstName();
    }

    @RequestMapping("/get-short-desc")
    @ResponseBody
    public ResponseEntity<List<DoctorShortDescription>> getShortDesc() {
        List<DoctorShortDescription> doctorShortDescriptions = doctorService.getShortDesc();
        return new ResponseEntity<List<DoctorShortDescription>>(doctorShortDescriptions, HttpStatus.OK);
    }

    @RequestMapping("/get-individual-desc/{id}")
    @ResponseBody
    public Optional<Doctor> getIndividualDesc(@PathVariable Long id) {
        Optional<Doctor> doctorIndividualDescription = doctorService.getIndividualDesc(id);
        return doctorIndividualDescription;
    }

    @RequestMapping("/add-doctor")
    @ResponseBody
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) throws URISyntaxException {
        doctorService.addDoctor(doctor);
        return new ResponseEntity<String>("doctor add successfully", HttpStatus.OK);
    }


}
