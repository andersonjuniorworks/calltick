package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.CalledStatus;
import com.andersonjunior.calltick.models.enums.ClientType;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.CalledRepository;
import com.andersonjunior.calltick.repositories.ClientRepository;
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

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private CalledRepository calledRepo;

    public void instantiateTestDatabase() throws ParseException {

        User u1 = new User(null, "ADMINISTRADOR", "admin@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        User u2 = new User(null, "ATENDIMENTO", "atendimento@admin.com", "printf@javadev", Profile.ADMINISTRADOR.getCode());
        userRepo.saveAll(Arrays.asList(u1, u2));
        
        Sector s1 = new Sector(null, "Suporte Técnico");
        Sector s2 = new Sector(null, "Suporte Operacional");
        Sector s3 = new Sector(null, "Financeiro");
        sectorRepo.saveAll(Arrays.asList(s1,s2,s3));

        Client c1 = new Client(null, ClientType.PESSOAFISICA.getCode(), "071.156.213-06", "Antonio Anderson Vieira do Nascimento Júnior", "Anderson Júnior", "63702-170", "Rua Manoel Balbino", "72", "Casa", "CE", "Crateús", "(88)99435-4507", "", "andersonjunior.tech@gmail.com", new Date());
        clientRepo.saveAll(Arrays.asList(c1));

        Called ca1 = new Called(null, c1, s1, u2, new Date(), null, u1.getFullname(), CalledStatus.ABERTO.getCode(), 0);
        calledRepo.saveAll(Arrays.asList(ca1));

    }
    
}
