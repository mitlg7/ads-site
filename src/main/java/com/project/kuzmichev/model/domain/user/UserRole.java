package com.project.kuzmichev.model.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    CLIENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
