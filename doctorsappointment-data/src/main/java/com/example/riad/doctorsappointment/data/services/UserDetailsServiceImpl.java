package com.example.riad.doctorsappointment.data.services;

import com.example.riad.doctorsappointment.data.domains.User;
import com.example.riad.doctorsappointment.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepo;
    public List<User> userList;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User getByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }


    public void addUser (User user){
        userRepo.save(user);
    }

    public List<User> getAllUsers(){
        if (userRepo.findAll() == null){
        }else{
            userRepo.findAll().forEach(userList:: add);
        }
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In userdetails method");
        User user = userRepo.findByUsername(username);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if (user == null){
            System.out.println("inside null");
            throw new UsernameNotFoundException(username);
        }

//        Set<GrantedAuthority> grantedAuthorities = setGrantedAuthorities(user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        System.out.println(user.getRoles());
        user.getRoles().forEach((role)->{
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

//    private Set<GrantedAuthority> setGrantedAuthorities(User user){
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        user.getRoles().forEach((role)->{
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
//        return grantedAuthorities;
//    }
}
