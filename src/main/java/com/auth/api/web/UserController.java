package com.auth.api.web;

import com.auth.api.model.SessionUser;
import com.auth.api.repository.SessionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = {"/oauth"},
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Validated
public class UserController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private SessionUserRepository sessionUserRepository;

    @RequestMapping(method = RequestMethod.DELETE, path = "/revoke")
    @ResponseStatus(HttpStatus.OK)
    public void revokeToken(Authentication authentication) {
        final String userToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        tokenServices.revokeToken(userToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerUser(@RequestBody @Valid SessionUser sessionUser) throws Exception {
        if (sessionUserRepository.findByEmailOrIdCode(sessionUser.getEmail()).isPresent()) {
            throw new DuplicateKeyException("Account with given e-mail already exists");
        }
        if (sessionUserRepository.findByEmailOrIdCode(sessionUser.getIdCode()).isPresent()) {
            throw new DuplicateKeyException("Account with given id code already exists");
        }
        sessionUser.setPassword(passwordEncoder.encode(sessionUser.getPassword()));
        sessionUserRepository.save(sessionUser);
    }
}
