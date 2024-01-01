package com.company.my_package.mapper;

import com.company.my_package.dto.UserDto;
import com.company.my_package.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDTO(User user);

    List<UserDto> entityToDTO(Iterable<User> users);

    User dtoToEntity(UserDto user);
}
