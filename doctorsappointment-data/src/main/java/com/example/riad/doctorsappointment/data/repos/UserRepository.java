package com.example.riad.doctorsappointment.data.repos;

import com.example.riad.doctorsappointment.data.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername (String username);
}
