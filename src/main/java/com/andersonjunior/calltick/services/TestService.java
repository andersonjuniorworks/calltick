package com.andersonjunior.calltick.services;

import java.text.ParseException;
import java.util.Arrays;

import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.repositories.SectorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private SectorRepository sectorRepo;

    public void instantiateTestDatabase() throws ParseException {
        
        Sector s1 = new Sector(null, "Suporte Técnico");
        Sector s2 = new Sector(null, "Suporte Operacional");
        Sector s3 = new Sector(null, "Financeiro");

        sectorRepo.saveAll(Arrays.asList(s1,s2,s3));
        
    }
    
}
