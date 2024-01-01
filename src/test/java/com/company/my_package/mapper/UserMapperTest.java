package com.company.my_package.mapper;

import com.company.my_package.dto.UserDto;
import com.company.my_package.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {UserMapperImpl.class})
class UserMapperTest {

    @Autowired
    private UserMapperImpl userMapper;

    @Test
    void testEntityToDto() {

        User user = User.builder()
                .id(12L)
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .creationDate(LocalDateTime.of(1, 1, 1, 1, 1))
                .updateDate(LocalDateTime.of(1, 1, 1, 1, 1))
                .build();

        UserDto userDto = this.userMapper.entityToDTO(user);

        assertEquals(userDto.id(), user.getId());
        assertEquals(userDto.firstName(), user.getFirstName());
        assertEquals(userDto.lastName(), user.getLastName());
        assertEquals(userDto.creationDate(), user.getCreationDate());
        assertEquals(userDto.updateDate(), user.getUpdateDate());
    }

    @Test
    void testDtoToEntity() {
        UserDto userDto = UserDto.builder()
                .id(12L)
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .creationDate(LocalDateTime.of(1, 1, 1, 1, 1))
                .updateDate(LocalDateTime.of(1, 1, 1, 1, 1))
                .build();

        User user = this.userMapper.dtoToEntity(userDto);

        assertEquals(user.getId(), userDto.id());
        assertEquals(user.getFirstName(), userDto.firstName());
        assertEquals(user.getLastName(), userDto.lastName());
        assertEquals(user.getCreationDate(), userDto.creationDate());
        assertEquals(user.getUpdateDate(), userDto.updateDate());
    }

}

