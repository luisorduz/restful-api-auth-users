package com.nisum.api.users.infraestructure.config.security.services;

import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.springdata.entity.UserEntity;
import com.nisum.api.users.infraestructure.springdata.mapper.UserMapperDbo;
import com.nisum.api.users.infraestructure.springdata.repository.UserPortRepositoryImpl;
import com.nisum.api.users.infraestructure.springdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserPortRepositoryImpl userPortRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPortRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
