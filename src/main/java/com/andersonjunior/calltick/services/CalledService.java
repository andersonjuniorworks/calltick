package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.CalledDto;
import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Transfers;
import com.andersonjunior.calltick.repositories.CalledRepository;
import com.andersonjunior.calltick.repositories.TransfersRespository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalledService {

    @Autowired
    private CalledRepository calledRepo;

    @Autowired
    private TransfersRespository transfersRespo;

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
        return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Called> findByStatus(int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size); 
        return calledRepo.findByStatus(status, pageable);
    }

    public List<Called> findByClient(Client client, int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size); 
        return calledRepo.findByClient(client, status, pageable);
    }

    public List<Called> findByPeriod(Date startDate, Date endDate, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size); 
        return calledRepo.findByPeriod(startDate, endDate, pageable);
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

    @Transactional
    public Called transfer(Called obj) {

        String teste = obj.getUser().getEmail();

        Transfers transfer = new Transfers();
        Called newObj = findById(obj.getId());

        transfer.setId(null);
        transfer.setCalled(obj);
        transfer.setResponsible(teste);
        transfer.setNewResponsible(newObj.getUser().getEmail());
        transfer.setDateOfTransfer(new Date());
        
        transfersRespo.save(transfer);
        updateData(newObj, obj);
        return calledRepo.save(newObj);

    }

    public Called fromDTO(CalledDto objDto) {
        return new Called(objDto.getId(), objDto.getClient(), objDto.getSector(), objDto.getUser(), objDto.getOpeningDate(), objDto.getClosingDate(), objDto.getOpenBy(), objDto.getStatus(), objDto.getActive());
    }
    
    private void updateData(Called newObj, Called obj) {
        newObj.setClient(obj.getClient());
        newObj.setSector(obj.getSector());
        newObj.setUser(obj.getUser());
        newObj.setOpenBy(obj.getOpenBy());
        newObj.setOpeningDate(obj.getOpeningDate());
        newObj.setClosingDate(obj.getClosingDate());
        newObj.setStatus(obj.getStatus());
        newObj.setActive(obj.getActive());
    }

}
