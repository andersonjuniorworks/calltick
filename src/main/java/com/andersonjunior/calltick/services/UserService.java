package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.UserDto;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.models.enums.Profile;
import com.andersonjunior.calltick.repositories.UserRepository;
import com.andersonjunior.calltick.security.UserSpringSecurity;
import com.andersonjunior.calltick.services.exceptions.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
	private BCryptPasswordEncoder encoder;

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch(Exception ex) {
            return null;
        }
    }

    public List<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable).getContent();
    }

    public User find(Long id) {

        UserSpringSecurity user = UserService.authenticated();

        if(user == null || !user.hasRole(Profile.ADMINISTRADOR) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso Negado!");
        }   

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
        obj.setAvatar("https://ui-avatars.com/api/?background=2667DF&color=fff&rounded=true&bold=true&length=2&name="+obj.getFullname());
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
        return new User(objDto.getId(), objDto.getFullname(), objDto.getEmail(), objDto.getPassword(), objDto.getIsActive(), objDto.getAvatar());
    }

    private void updateData(User newObj, User obj) {
        newObj.setFullname(obj.getFullname());
        newObj.setEmail(obj.getEmail());
    }

}
