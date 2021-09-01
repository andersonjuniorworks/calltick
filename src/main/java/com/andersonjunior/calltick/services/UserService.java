package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.UserDto;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository clientRepo;

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepo.findAll(pageable).getContent();
    }

    public User find(Integer id) {
        Optional<User> obj = clientRepo.findById(id);
        return obj.orElseThrow();
    }

    public List<User> findByFullname(String fullname) {
        return clientRepo.findByFullname(fullname);
    }

    @Transactional
    public User insert(User obj) {
        obj.setId(null);
        return clientRepo.save(obj);
    }

    @Transactional
    public User update(User obj) {
        User newObj = find(obj.getId());
        updateData(newObj, obj);
        return clientRepo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clientRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir este usuário!");
        }
    }

    public User fromDTO(UserDto objDto) {
        return new User(objDto.getId(), objDto.getFullname(), objDto.getEmail(), null, null);
    }

    private void updateData(User newObj, User obj) {
        newObj.setFullname(obj.getFullname());
        newObj.setEmail(obj.getEmail());
        newObj.setPassword(obj.getPassword());
        newObj.setProfile(obj.getProfile());
    }

}
