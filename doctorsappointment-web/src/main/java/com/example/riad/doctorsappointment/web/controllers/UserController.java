package com.example.riad.doctorsappointment.web.controllers;

import com.example.riad.doctorsappointment.data.domains.User;
import com.example.riad.doctorsappointment.data.services.Interfaces.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private CustomUserService customUserService;

    @Autowired
    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @RequestMapping("/add-user")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody User user){
        System.out.println("add user method!!!");
        customUserService.save(user);
        return new ResponseEntity<String>("User add successfully", HttpStatus.OK);
    }

    @RequestMapping("/get-allUser")
    @ResponseBody
    public List<User> getAllUser(){
        System.out.println("get all users!!");
        List<User> users = new ArrayList<>();
        return customUserService.findAllUsers();
    }

}
