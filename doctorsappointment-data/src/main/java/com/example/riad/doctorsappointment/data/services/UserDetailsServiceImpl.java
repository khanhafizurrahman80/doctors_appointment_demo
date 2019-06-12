package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.CustomUserDetails;
import com.example.riad.doctorsappointment.data.domains.User;
import com.example.riad.doctorsappointment.data.repos.UserRepository;
import com.example.riad.doctorsappointment.data.services.Interfaces.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, CustomUserService {

    private UserRepository userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);

        optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return optionalUser.map(CustomUserDetails::new).get();
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users:: add);
        return users;
    }
}
