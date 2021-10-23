package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.CompanyDto;
import com.andersonjunior.calltick.models.Company;
import com.andersonjunior.calltick.repositories.CompanyRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepo;

    @Autowired
    public CompanyService(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> findAll() {
        return companyRepo.findAll();
    }

    public Company findById(Long id) {
        Optional<Company> obj = companyRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada na base de dados!!!"));
    }

    public Long count() {
        Long count = companyRepo.count();
        return count;
    }

    @Transactional
    public Company insert(Company obj) {
        obj.setId(null);
        return companyRepo.save(obj);
    }

    @Transactional
    public Company update(Company obj) {
        Company newObj = findById(obj.getId());
        updateData(newObj, obj);
        return companyRepo.save(newObj);
    }

    public Company fromDTO(CompanyDto objDto) {
        return new Company(objDto.getId(), objDto.getType(), objDto.getDocument(), objDto.getStateRegistration(), objDto.getFullname(),
        objDto.getNickname(), objDto.getZipcode(), objDto.getAddress(), objDto.getHomeNumber(),
        objDto.getComplement(), objDto.getNeighborhood(), objDto.getState(), objDto.getCity(), objDto.getPhoneNumberOne(),
        objDto.getPhoneNumberTwo(), objDto.getEmail(), objDto.getLogo(), new Date(), null);
    }

    private void updateData(Company newObj, Company obj) {
        newObj.setStateRegistration(obj.getStateRegistration());
        newObj.setFullname(obj.getFullname());
        newObj.setNickname(obj.getNickname());
        newObj.setZipcode(obj.getZipcode());    
        newObj.setAddress(obj.getAddress());
        newObj.setHomeNumber(obj.getHomeNumber());
        newObj.setComplement(obj.getComplement());
        newObj.setNeighborhood(obj.getNeighborhood());
        newObj.setState(obj.getState());
        newObj.setCity(obj.getCity());
        newObj.setPhoneNumberOne(obj.getPhoneNumberOne());
        newObj.setPhoneNumberTwo(obj.getPhoneNumberTwo());
        newObj.setEmail(obj.getEmail());
        newObj.setLogo(obj.getLogo());
        newObj.setUpdateAt(new Date());
    }

}
