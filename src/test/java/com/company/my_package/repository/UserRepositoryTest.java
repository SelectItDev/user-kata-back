package com.company.my_package.repository;

import com.company.my_package.model.User;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ComponentScan(basePackages = {"com.company.my_package.repository"})
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAll() {
        // given
        entityManager.merge(User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build());
        entityManager.merge(User.builder().id(2L).firstName("fn2").lastName("ln2").email("email2@compagny.com").build());
        entityManager.merge(User.builder().id(3L).firstName("fn3").lastName("ln3").email("email3@compagny.com").build());
        entityManager.flush();

        // when
        Iterable<User> users = userRepository.findAll();

        // then
        assertEquals(3, IterableUtils.size(users));
    }

    @Test
    void testFindUserByEmail() {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        entityManager.merge(user);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findByEmail(user.getEmail());

        // then
        assertEquals(found.get().getEmail(), user.getEmail());
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
    void testSaveUser_modification() {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        entityManager.merge(user);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findByEmail(user.getEmail());
        found.ifPresent(user1 -> user1.setFirstName("firstNameUpdated"));
        User foundUpdated = userRepository.save(found.get());

        // then
        assertEquals("firstNameUpdated", foundUpdated.getFirstName());
    }

    @Test
    void testDeleteById() {
        // given
        User user = User.builder().id(1L).firstName("fn1").lastName("ln1").email("email1@compagny.com").build();
        entityManager.merge(user);
        entityManager.flush();

        // when
        userRepository.deleteById(user.getId());
        Optional<User> found = userRepository.findByEmail(user.getEmail());

        // then
        assertFalse(found.isPresent());
    }

}

