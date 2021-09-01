package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Arrays;

import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.SectorRepository;
import com.andersonjunior.calltick.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private SectorRepository sectorRepo;

    @Autowired
    private UserRepository userRepo;

    public void instantiateTestDatabase() throws ParseException {

        User u1 = new User(null, "ADMINISTRADOR", "admin@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        User u2 = new User(null, "ATENDIMENTO", "atendimento@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        userRepo.saveAll(Arrays.asList(u1));
        
        Sector s1 = new Sector(null, "Suporte Técnico");
        Sector s2 = new Sector(null, "Suporte Operacional");
        Sector s3 = new Sector(null, "Financeiro");

        sectorRepo.saveAll(Arrays.asList(s1,s2,s3));
        
    }
    
}
