package com.registration.service.impl;

import com.registration.exception.RegistrationConstants;
import com.registration.exception.UserExistException;
import com.registration.repository.UserRepository;
import com.registration.service.UserService;
import com.shop.core.mapper.EntityFactory;
import com.shop.core.model.dto.UserDto;
import com.shop.core.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private Set<String> userCache = new HashSet<>();


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = EntityFactory.convertToUser(userDto);
        if (!userCache.contains(user.getAccount())){
            userCache.add(user.getAccount());
            return userRepository.save(user);
        }
        throw new UserExistException(RegistrationConstants.DUPLICATE_USER_REGISTRATION);
    }

    @Override
    public Optional<User> findUserByUserId(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAllUserPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
