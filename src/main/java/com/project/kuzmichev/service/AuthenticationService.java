package com.project.kuzmichev.service;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.security.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface  AuthenticationService {
    AuthenticationResponse login(User user);
    AuthenticationResponse register(User user);
    boolean canRegister(User user);
    boolean canLogin(User user);
}
