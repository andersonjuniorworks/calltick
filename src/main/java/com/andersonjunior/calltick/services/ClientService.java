package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.repositories.ClientRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

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

    public Client find(Long id) {
        Optional<Client> obj = clientRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado na base de dados!!!"));
    }

    public Long count() {
        Long count = clientRepo.count();
        return count;
    }

    public List<Client> findByDocument(String document) {
        return clientRepo.findByDocument(document);
    }

    public List<Client> findByFullname(String fullname) {
        return clientRepo.findByFullnameContainingIgnoreCase(fullname);
    }

    public List<Client> findByNickname(String nickname) {
        return clientRepo.findByNicknameContainingIgnoreCase(nickname);
    }

    public List<Client> findByCity(String city) {
        return clientRepo.findByCityIgnoreCase(city);
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        return clientRepo.save(obj);
    }

    @Transactional
    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return clientRepo.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            clientRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir clientes com chamados ou pagamentos vinculados");
        }
    }

    public Client fromDTO(ClientDto objDto) {
        return new Client(objDto.getId(), objDto.getType(), objDto.getDocument(), objDto.getFullname(),
                objDto.getNickname(), objDto.getZipcode(), objDto.getAddress(), objDto.getHomeNumber(),
                objDto.getComplement(), objDto.getState(), objDto.getCity(), objDto.getPhoneNumberOne(),
                objDto.getPhoneNumberTwo(), objDto.getEmail(), new Date());
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setType(obj.getType());
        newObj.setFullname(obj.getFullname());
        newObj.setNickname(obj.getNickname());
        newObj.setZipcode(obj.getZipcode());    
        newObj.setAddress(obj.getAddress());
        newObj.setHomeNumber(obj.getHomeNumber());
        newObj.setComplement(obj.getComplement());
        newObj.setState(obj.getState());
        newObj.setCity(obj.getCity());
        newObj.setPhoneNumberOne(obj.getPhoneNumberOne());
        newObj.setPhoneNumberTwo(obj.getPhoneNumberTwo());
        newObj.setEmail(obj.getEmail());
    }

}
