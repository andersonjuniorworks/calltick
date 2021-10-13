package com.andersonjunior.calltick.repositories;

import java.util.List;

import com.andersonjunior.calltick.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFullnameContainingIgnoreCase(String fullname);
    
    User findByEmail(String email);
    
}
