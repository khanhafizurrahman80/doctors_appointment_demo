package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.Doctor;
import com.example.riad.doctorsappointment.data.services.DoctorDetailsService;
import com.example.riad.doctorsappointment.data.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.PanelUI;
import javax.transaction.Transactional;
import java.util.List;

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

    @RequestMapping("/get-full-name")
    @ResponseBody
    public String getFirstName() {
        return doctorService.getFirstName();
    }
}
