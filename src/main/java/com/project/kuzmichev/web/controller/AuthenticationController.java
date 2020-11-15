package com.project.kuzmichev.web.controller;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.security.AuthenticationResponse;
import com.project.kuzmichev.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        if(authenticationService.canRegister(user)) {
            AuthenticationResponse response = authenticationService.register(user);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Пользователь с данным именем или email уже существует.");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        System.out.println(user.toString());
        if(authenticationService.canLogin(user)) {
            AuthenticationResponse response = authenticationService.login(user);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Не удалось войти в систему. Проверьте логин и пароль и повторите снова.");
    }
}
