package com.andersonjunior.calltick.services;

import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.repositories.UserRepository;
import com.andersonjunior.calltick.security.UserSpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getProfiles());
    
    }
    
}
