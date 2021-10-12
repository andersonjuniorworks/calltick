package com.andersonjunior.calltick.services;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable).getContent();
    }

    public User find(Long id) {
        Optional<User> obj = userRepo.findById(id);
        obj.get().getPassword();
        return obj.orElseThrow();
    }

    public Long count() {
        Long count = userRepo.count();
        return count;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<User> findByFullname(String fullname) {
        return userRepo.findByFullnameContainingIgnoreCase(fullname);
    }

    @Transactional
    public User insert(User obj) {
        obj.setId(null);
        obj.setPassword(encoder.encode(obj.getPassword()));
        return userRepo.save(obj);
    }

    @Transactional
    public User update(User obj) {
        if (obj.getId() != 1) {
            User newObj = find(obj.getId());
            updateData(newObj, obj);
            return userRepo.save(newObj);
        } else {
            throw new DataIntegrityViolationException("Não é possível editar o usuário Administrador!");
        }
    }

    public void delete(Long id) {
        find(id);
        if (id != 1) {
            try {
                userRepo.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("Não é possível excluir usuário com chamados vinculados!");
            }
        } else {
            throw new DataIntegrityViolationException("Não é possível excluir o usuário administrador!");
        }
    }

    public User fromDTO(UserDto objDto) {
        return new User(objDto.getId(), objDto.getFullname(), objDto.getEmail(), objDto.getPassword(),
                objDto.getProfile());
    }

    private void updateData(User newObj, User obj) {
        newObj.setFullname(obj.getFullname());
        newObj.setEmail(obj.getEmail());
        newObj.setProfile(obj.getProfile());
    }

}
