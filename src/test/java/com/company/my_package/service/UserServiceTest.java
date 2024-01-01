package com.company.my_package.service;

import com.company.my_package.dto.UserDto;
import com.company.my_package.exception.ResourceNotDeletedException;
import com.company.my_package.exception.ResourceNotSavedException;
import com.company.my_package.exception.SearchFailedException;
import com.company.my_package.model.User;
import com.company.my_package.repository.UserRepository;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@ComponentScan(basePackages = {
        "com.company.my_package.service",
        "com.company.my_package.mapper"})
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void testGetUsers() throws SearchFailedException {
        // given
        userRepository.save(User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build());
        userRepository.save(User.builder().id(2L).firstName("fn2").lastName("ln2").email("email2@compagny.com").build());
        userRepository.save(User.builder().id(3L).firstName("fn3").lastName("ln3").email("email3@compagny.com").build());

        // when
        Iterable<UserDto> users = userService.getUsers();

        // then
        assertEquals(3, IterableUtils.size(users));
    }

    @Test
    void testFindUserByEmail() {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        userRepository.save(user);

        // when
        UserDto found = userService.findUserByEmail(user.getEmail());

        // then
        assertEquals(found.email(), user.getEmail());
    }

    @Test
    void testSaveUser_creation() {
        // given
        User user = User.builder().id(null).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();

        // when
        User userCreated = userRepository.save(user);

        // then
        assertNotNull(userCreated);
        assertNotNull(userCreated.getId());
    }

    @Test
    void testSaveUser_modification() throws ResourceNotSavedException {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email12@compagny.com").build();
        userRepository.save(user);

        // when
        UserDto found = userService.findUserByEmail(user.getEmail());
        if(found != null){
            UserDto foundToUpdate = UserDto.builder()
                    .id(found.id())
                    .firstName("firstNameUpdated")
                    .lastName(found.lastName())
                    .email(found.email())
                    .creationDate(found.creationDate())
                    .updateDate(found.updateDate())
                    .build();

            UserDto foundUpdated = userService.saveUser(foundToUpdate);

            // then
            assertEquals("firstNameUpdated", foundUpdated.firstName());
        }

    }

    @Test
    void testDeleteById() throws ResourceNotDeletedException {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        userRepository.save(user);

        // when
        userService.deleteUser(user.getId());
        UserDto found = userService.findUserByEmail(user.getEmail());

        // then
        assertNull(found);
    }

}

