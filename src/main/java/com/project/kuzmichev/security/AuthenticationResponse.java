package com.project.kuzmichev.security;

import com.project.kuzmichev.model.domain.user.User;
import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;
    private String username;
    private String email;
    private String role;

    public AuthenticationResponse(User user, String token) {
        this.token = token;
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getUserRole().toString();
    }
}
