package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.CalledDto;
import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.repositories.CalledRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalledService {

    @Autowired
    private CalledRepository calledRepo;

    public List<Called> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size); 
        return calledRepo.findAll(pageable).getContent();
    }

    public List<Called> findAllCalls(Integer active, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size); 
        return calledRepo.findByActive(active, pageable);
    }

    public Called findById(Long id) {
        Optional<Called> obj = calledRepo.findById(id);
        return obj.orElseThrow();
    }

    public List<Called> findByClient(Client client, int status) {
        return calledRepo.findByClient(client, status);
    }

    public List<Called> findByPeriod(Date startDate, Date endDate){
        return calledRepo.findByPeriod(startDate, endDate);
    }

    @Transactional
    public Called insert(Called obj) {
        obj.setId(null);
        return calledRepo.save(obj);
    }

    @Transactional
    public Called update(Called obj) {
        Called newObj = findById(obj.getId());
        updateData(newObj, obj);
        return calledRepo.save(newObj);
    }

    @Transactional
    public Called delete(Called obj) {
        Called newObj = findById(obj.getId());
        obj.setActive(1);
        updateData(newObj, obj);
        return calledRepo.save(newObj);
    }

    public Called fromDTO(CalledDto objDto) {
        return new Called(objDto.getId(), objDto.getClient(), objDto.getSector(), objDto.getUsers(), objDto.getOpeningDate(), objDto.getClosingDate(), objDto.getOpenBy(), objDto.getStatus(), objDto.getActive());
    }
    
    private void updateData(Called newObj, Called obj) {
        newObj.setClient(obj.getClient());
        newObj.setSector(obj.getSector());
        newObj.setOpenBy(obj.getOpenBy());
        newObj.setOpeningDate(obj.getOpeningDate());
        newObj.setClosingDate(obj.getClosingDate());
        newObj.setStatus(obj.getStatus());
        newObj.setActive(obj.getActive());
    }

}
