package com.example.riad.doctorsappointment.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping({"","/","/index"})
    @ResponseBody
    public String getIndexPage() {
        return "I am at the index page!";
    }
}
