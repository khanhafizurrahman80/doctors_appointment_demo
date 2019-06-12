package com.example.riad.doctorsappointment.data.services.Interfaces;

import com.example.riad.doctorsappointment.data.domains.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserService {
    void save(User user);
    List<User> findAllUsers();

}
