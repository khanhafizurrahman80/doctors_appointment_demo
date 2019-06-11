package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.User;
import com.example.riad.doctorsappointment.data.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    public RegistrationController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @RequestMapping("/add-user")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user) throws URISyntaxException {
        System.out.println("User add Controller");
        userDetailsServiceImpl.addUser(user);
        return new ResponseEntity<String>("User add successfully", HttpStatus.OK);
    }

    @RequestMapping("/get-all")
    @ResponseBody
    public List<User> getAllDoctors(){
        System.out.println("all doctors get");
        int count = userDetailsServiceImpl.getAllUsers().size();
        log.debug("get all doctors " + count);
        return userDetailsServiceImpl.getAllUsers();
    }
}
