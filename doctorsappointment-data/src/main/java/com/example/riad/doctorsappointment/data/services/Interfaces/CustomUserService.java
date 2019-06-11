package com.example.riad.doctorsappointment.data.services.Interfaces;

import com.example.riad.doctorsappointment.data.domains.User;

import java.util.List;

public interface CustomUserService {
    void save(User user);
    User findByUsername(String username);
    List<User> findAllUsers();

}
