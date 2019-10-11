package com.auth.api.service;

import com.auth.api.model.SessionUser;
import com.auth.api.repository.SessionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Collections.emptySet;

@Component
public class DefaultUserDetailsService implements UserDetailsService {

    private final SessionUserRepository sessionUserRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public DefaultUserDetailsService(SessionUserRepository sessionUserRepository) {
        this.sessionUserRepository = sessionUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<SessionUser> userEntity = sessionUserRepository.findByEmailOrIdCode(username);
        if (userEntity.isPresent()) {
            final SessionUser sessionUser = userEntity.get();
            return new User(sessionUser.getEmail(),
                    sessionUser.getPassword(),
                    emptySet());
        }
        return null;
    }


}
