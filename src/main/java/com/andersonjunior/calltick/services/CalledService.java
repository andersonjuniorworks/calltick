package com.andersonjunior.calltick.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.CalledDto;
import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.Transfers;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.CalledStatus;
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

    public List<Called> findCalls(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findCalls(pageable);
    }

    public List<Called> findByUser(User user, Integer status, Integer active, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findByUserAndStatusAndActive(user, status, active, pageable);
    }

    public Integer countByUser(User user, Integer status) {
        return calledRepo.findByUserAndStatus(user, status).size();
    }

    public Long count() {
        Long count = calledRepo.count();
        return count;
    }

    public Called findById(Long id) {
        Optional<Called> obj = calledRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Called> findAllCalls(Integer status, Integer active, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findByStatusAndActiveOrderByIdDesc(status, active, pageable);
    }

    public List<Called> findByClient(Client client, int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findByClient(client, status, pageable);
    }

    public List<Called> findByParams(Client client, User user, Sector sector, int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findByClientAndUserAndSectorAndStatus(client, user, sector, status, pageable);
    }

    public List<Called> findBySector(Sector sector, int status, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findBySectorAndStatus(sector, status, pageable);
    }

    @Transactional
    public Called insert(Called obj) {
        SimpleDateFormat formatOpeningDate = new SimpleDateFormat();
        obj.setId(null);
        obj.setOpeningDate(formatOpeningDate.format(new Date()));
        obj.setStatus(CalledStatus.ABERTO.getCode());
        obj.setActive(0);
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
    public Called finishCalled(Called obj) {

        SimpleDateFormat formatClosingDate = new SimpleDateFormat();

        Called newObj = findById(obj.getId());
        
        obj.setClosingDate(formatClosingDate.format(new Date()));
        obj.setStatus(CalledStatus.FINALIZADO.getCode());

        updateData(newObj, obj);
        return calledRepo.save(newObj);
    }

    @Transactional
    public Called transfer(Called obj) {

        String newUser = obj.getUser().getEmail();

        Transfers transfer = new Transfers();
        Called newObj = findById(obj.getId());

        transfer.setId(null);
        transfer.setCalled(obj);
        transfer.setResponsible(newObj.getUser().getEmail());
        transfer.setNewResponsible(newUser);
        transfer.setDateOfTransfer(new Date());

        transfersRespo.save(transfer);
        updateData(newObj, obj);
        return calledRepo.save(newObj);

    }

    public Called fromDTO(CalledDto objDto) {
        return new Called(objDto.getId(), objDto.getClient(), objDto.getTypeService(), objDto.getSector(), objDto.getSubject(),
                objDto.getDescription(), objDto.getUser(), objDto.getOpeningDate(), objDto.getClosingDate(),
                objDto.getOpenBy(), objDto.getCloseBy(), objDto.getTechnicalReport(), objDto.getStatus(), objDto.getActive(), objDto.getCreatedAt());
    }

    private void updateData(Called newObj, Called obj) {
        newObj.setClient(obj.getClient());
        newObj.setTypeService(obj.getTypeService());
        newObj.setSubject(obj.getSubject());
        newObj.setDescription(obj.getDescription());
        newObj.setSector(obj.getSector());
        newObj.setUser(obj.getUser());
        newObj.setOpenBy(obj.getOpenBy());
        newObj.setCloseBy(obj.getCloseBy());
        newObj.setTechnicalReport(obj.getTechnicalReport());
        newObj.setOpeningDate(obj.getOpeningDate());
        newObj.setClosingDate(obj.getClosingDate());
        newObj.setStatus(obj.getStatus());
        newObj.setActive(obj.getActive());
    }

}
