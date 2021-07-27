package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

    public Called findById(Integer id) {
        Optional<Called> obj = calledRepo.findById(id);
        return obj.orElseThrow();
    }

    public List<Called> findByClient(Client client, int status) {
        List<Called> obj = calledRepo.findByClient(client, status);
		return obj;
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
    
    private void updateData(Called newObj, Called obj) {
        newObj.setClient(obj.getClient());
        newObj.setSector(obj.getSector());
        newObj.setOpenBy(obj.getOpenBy());
        newObj.setOpeningDate(obj.getOpeningDate());
        newObj.setClosingDate(obj.getClosingDate());
        newObj.setStatus(obj.getStatus());
    }

}
