package com.company.my_package.service;

import com.company.my_package.dto.UserDto;
import com.company.my_package.exception.ResourceNotDeletedException;
import com.company.my_package.exception.ResourceNotSavedException;
import com.company.my_package.exception.SearchFailedException;

public interface UserService {

    Iterable<UserDto> getUsers() throws SearchFailedException;

    UserDto saveUser(UserDto userDto) throws ResourceNotSavedException;

    void deleteUser(Long id) throws ResourceNotDeletedException;

    UserDto findUserByEmail(String email);
}
