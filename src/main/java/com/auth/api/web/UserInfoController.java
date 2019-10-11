package com.auth.api.web;

import com.auth.api.model.SessionUser;
import com.auth.api.model.SessionUserDto;
import com.auth.api.repository.SessionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(
        value = {"/api/user"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
public class UserInfoController {

    @Autowired
    SessionUserRepository userRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SessionUserDto getUser(Authentication authentication) {
        final String userName = authentication.getName();
        Optional<SessionUser> sessionUserOptional = userRepository.findByEmailOrIdCode(userName);
        if (sessionUserOptional.isPresent()) {
            SessionUser sessionUser = sessionUserOptional.get();
            return SessionUserDto
                    .builder()
                    .firstName(sessionUser.getFirstName())
                    .lastName(sessionUser.getLastName())
                    .birthday(sessionUser.getBirthday())
                    .email(sessionUser.getEmail())
                    .idCode(sessionUser.getIdCode())
                    .build();
        }
        return null;
    }

}
