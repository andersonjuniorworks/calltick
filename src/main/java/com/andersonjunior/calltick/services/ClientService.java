package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public List<Client> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size); 
        return clientRepo.findAll(pageable).getContent();
    }

    public Client findById(Integer id) {
        Optional<Client> obj = clientRepo.findById(id);
        return obj.orElseThrow();
    }

    public Client findByCpfOrCnpj(String cpfOrCnpj) {
        Client obj = clientRepo.findByCpfOrCnpj(cpfOrCnpj);
		return obj;
    }

    public List<Client> findByDescription(String fullname){
        return clientRepo.findByFullname(fullname);
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        return clientRepo.save(obj);
    }

    @Transactional
    public Client update(Client obj) {
        Client newObj = findById(obj.getId());
        updateData(newObj, obj);
        return clientRepo.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            clientRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir este cliente!!!");
        }
    }

    public Client fromDTO(ClientDto objDto) {
        return new Client(objDto.getId(), objDto.getType(), objDto.getCpfOrCnpj(), objDto.getFullname(), objDto.getNickname(),
        objDto.getZipcode(), objDto.getAddress(), objDto.getHomeNumber(), objDto.getComplement(), objDto.getState(), objDto.getCity(), 
        objDto.getPhoneNumberOne(), objDto.getPhoneNumberTwo(), objDto.getEmail(), new Date());
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setType(obj.getType());
        newObj.setFullname(obj.getFullname());
        newObj.setNickname(obj.getNickname());
        newObj.setZipcode(obj.getZipcode());
        newObj.setZipcode(obj.getAdress());
        newObj.setZipcode(obj.getHomeNumber());
        newObj.setZipcode(obj.getComplement());
        newObj.setZipcode(obj.getState());
        newObj.setZipcode(obj.getCity());
        newObj.setZipcode(obj.getPhoneNumberOne());
        newObj.setZipcode(obj.getPhoneNumberTwo());
        newObj.setZipcode(obj.getEmail());
    }
    
}
