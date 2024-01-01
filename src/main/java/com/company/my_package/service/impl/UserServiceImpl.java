package com.company.my_package.service.impl;

import com.company.my_package.dto.UserDto;
import com.company.my_package.exception.ResourceNotDeletedException;
import com.company.my_package.exception.ResourceNotSavedException;
import com.company.my_package.exception.SearchFailedException;
import com.company.my_package.mapper.UserMapper;
import com.company.my_package.model.User;
import com.company.my_package.repository.UserRepository;
import com.company.my_package.service.UserService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.map(userMapper::entityToDTO).orElse(null);
    }

    @Override
    public Iterable<UserDto> getUsers() throws SearchFailedException {

        Iterable<User> user;

        try {
            user = userRepository.findAll();
        } catch (Exception e) {
            throw new SearchFailedException("Cannot get users from database :\n" +
                    ExceptionUtils.getRootCause(e));
        }

        return userMapper.entityToDTO(user);
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ResourceNotSavedException {

        User user = userMapper.dtoToEntity(userDto);

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceNotSavedException("Cannot save user to database :\n" +
                    ExceptionUtils.getRootCause(e));
        }

        return userMapper.entityToDTO(user);
    }

    @Override
    public void deleteUser(Long id) throws ResourceNotDeletedException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotDeletedException(String.format("cannot delete user with id : '%s'", id) +  "\n" +
                    ExceptionUtils.getRootCause(e));
        }
    }
}
