package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Arrays;

import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProdService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void instantiateProdDatabase() throws ParseException {

        User user = userRepo.findByEmail("andersonjunior.dev@gmail.com");

        if (user == null) {
            User u1 = new User(null, "Administrador", "andersonjunior.dev@gmail.com", encoder.encode("printf@javadev"),
                    Profile.ADMINISTRADOR.getCode());
            userRepo.saveAll(Arrays.asList(u1));
        }

    }

}
