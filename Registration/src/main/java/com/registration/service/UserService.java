package com.registration.service;

import com.shop.core.model.dto.UserDto;
import com.shop.core.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User saveUser(UserDto userDto);

    Optional<User> findUserByUserId(String id);

    Page<User> findAllUserPageable(Pageable pageable);
}
