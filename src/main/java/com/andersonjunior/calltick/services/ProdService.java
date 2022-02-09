package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Arrays;

import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProdService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instantiateProdDatabase() throws ParseException {

        User user = userRepo.findByEmail("andersonjunior.dev@gmail.com");

        if (user == null) {
            User u1 = new User(null, "Administrador", "andersonjunior.dev@gmail.com", encoder.encode("printf@javadev"), 0, "https://ui-avatars.com/api/?background=6731ec&color=fff&rounded=true&bold=true&name=ADMINISTRADOR&length=2");
            userRepo.saveAll(Arrays.asList(u1));
        }

    }

}
