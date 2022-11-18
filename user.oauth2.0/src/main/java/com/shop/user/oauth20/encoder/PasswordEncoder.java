package com.shop.user.oauth20.encoder;

public interface PasswordEncoder {
    String hashPassword(String password);
}
