package com.shop.core.model.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPERADMIN, ADMIN,NORMAL;

    @Override
    public String getAuthority() {
        return name();
    }
}
