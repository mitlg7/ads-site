package com.project.kuzmichev.service;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.domain.user.UserRole;
import com.project.kuzmichev.model.repository.UserRepository;
import com.project.kuzmichev.security.AuthenticationResponse;
import com.project.kuzmichev.security.JwtTokenProvider;
import com.project.kuzmichev.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private EmailService emailService;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ADMIN);
        userRepository.save(user);
        String token = tokenProvider.generateToken(user.getUsername());
        emailService.sendSimpleMessage(user.getEmail()
                ,"Успешная регистрация",
                "Добро пожаловать на сайт Alito64!\n" +
                "На нашем сайте вы можете быстро и выгодно купить или продать вещи\n" +
                "Загрузи свою первое объявление прямо сейчас !");
        return new AuthenticationResponse(user, token);
    }

    @Override
    public boolean canLogin(User logged) {
        User user = userRepository.findByUsername(logged.getUsername());
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
