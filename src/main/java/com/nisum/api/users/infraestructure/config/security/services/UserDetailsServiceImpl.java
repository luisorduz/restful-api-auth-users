package com.nisum.api.users.infraestructure.config.security.services;

import com.nisum.api.users.domain.model.User;
import com.nisum.api.users.infraestructure.springdata.repository.UserPortRepositoryImpl;
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

    /*
    *  método para cargar Usuario por nombre de usuario y devuelve un UserDetailsobjeto
    *  que Spring Security puede usar para autenticación y validación.
    * */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPortRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
