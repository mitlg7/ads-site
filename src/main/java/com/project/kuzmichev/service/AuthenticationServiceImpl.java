package com.project.kuzmichev.service;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.domain.user.UserRole;
import com.project.kuzmichev.model.repository.UserRepository;
import com.project.kuzmichev.security.AuthenticationResponse;
import com.project.kuzmichev.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Override
    public AuthenticationResponse login(User logged) {
        User user = userRepository.findByUsername(logged.getUsername());
        String token = tokenProvider.generateToken(user.getUsername());

        return new AuthenticationResponse(user, token);
    }
    @Override
    public AuthenticationResponse register(User user) {
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        user.setUserRole(UserRole.CLIENT);
        userRepository.save(user);
        String token = tokenProvider.generateToken(user.getUsername());

        return new AuthenticationResponse(user, token);
    }

    @Override
    public boolean canLogin(User logged) {
        User user = userRepository.findByUsername(logged.getUsername());
        System.out.println(user.toString());
        System.out.println(isPasswordMatches(logged.getPassword(),user.getPassword()));
        return user != null && isPasswordMatches(logged.getPassword(), user.getPassword());
    }
    private boolean isPasswordMatches(String unencodedPassword, String encodedPassword) {
        return passwordEncoder.matches(unencodedPassword, encodedPassword);
    }
    public boolean canRegister(User user) {
        boolean isUserAlreadyExists = userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());
        return !isUserAlreadyExists;
    }
}
