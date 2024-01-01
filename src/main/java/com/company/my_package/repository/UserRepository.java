package com.company.my_package.repository;


import com.company.my_package.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
