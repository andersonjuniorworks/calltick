package com.andersonjunior.calltick.services;

import java.text.ParseException;
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
import com.andersonjunior.calltick.repositories.CalledCustomRepository;
import com.andersonjunior.calltick.repositories.CalledRepository;
import com.andersonjunior.calltick.repositories.TransfersRespository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class CalledService {

    @Autowired
    private CalledRepository calledRepo;

    @Autowired
    private TransfersRespository transfersRespo;

    @Autowired
	private EmailService emailService;

    private final CalledCustomRepository calledCustomRepo;

    @Autowired
    public CalledService(CalledCustomRepository calledCustomRepo) {
        this.calledCustomRepo = calledCustomRepo;
    }
    
    public List<Called> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findAll(pageable).getContent();
    }

    public List<Called> findCalls(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findCalls(pageable);
    }

    public Called findById(Long id) {
        Optional<Called> obj = calledRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado na base de dados"));
    }

    public List<Called> findAllCalls(Integer status, Integer active, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return calledRepo.findByStatusAndActiveOrderByIdDesc(status, active, pageable);
    }

    public List<Called> findByFilter(Client client, User user, Sector sector, Integer status, String startDate, String endDate) throws ParseException {
        return calledCustomRepo.find(client, user, sector, status, startDate, endDate);
    }

    public List<Called> findByPeriod(Date startDate, Date endDate) {
        return calledRepo.findByPeriod(startDate, endDate);
    }

    public Integer countByFilter(Client client, User user, Sector sector, Integer status, String startDate, String endDate) throws ParseException {
        return calledCustomRepo.count(client, user, sector, status, startDate, endDate);
    }

    public Integer countByUser(User user, Date startDate, Date endDate) {
        return calledRepo.countCustom(user, startDate, endDate);
    }

    public Integer countByStatus(Integer status) {
        return calledRepo.countByStatus(status);
    }
    
    public Long count() {
        Long count = calledRepo.count();
        return count;
    }

    @Transactional
    public Called insert(Called obj) {
        SimpleDateFormat formatOpeningDate = new SimpleDateFormat();
        obj.setId(null);
        obj.setOpeningDate(formatOpeningDate.format(new Date()));
        obj.setCreatedAt(new Date());
        obj.setStatus(CalledStatus.ABERTO.getCode());
        obj.setActive(0);
        calledRepo.save(obj);
        emailService.sendTicketConfirmationHtmlEmail(obj);
        return obj;
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
        emailService.sendTicketFinishEmail(obj);
        return calledRepo.save(newObj);
    }

    @Transactional
    public Called reopenCalled(Called obj) {
        Called newObj = findById(obj.getId());
        obj.setStatus(CalledStatus.ABERTO.getCode());
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
                objDto.getOpenBy(), objDto.getCloseBy(), objDto.getStatus(), objDto.getTechnicalReport(), objDto.getActive(), objDto.getCreatedAt());
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
        newObj.setOpeningDate(obj.getOpeningDate());
        newObj.setClosingDate(obj.getClosingDate());
        newObj.setStatus(obj.getStatus());
        newObj.setTechnicalReport(obj.getTechnicalReport());
        newObj.setActive(obj.getActive());
    }

    

}
