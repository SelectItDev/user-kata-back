package com.company.my_package.controller;

import com.company.my_package.dto.UserDto;
import com.company.my_package.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Tag(name = "Users managment Rest API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get a list of users")
    public ResponseEntity<Iterable<UserDto>> users() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionUtils.getStackTrace(e), e);
        }
    }

    @GetMapping("/users/email/{email}")
    @Operation(summary = "Find a user by his email")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("/users")
    @Operation(summary = "Save a user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionUtils.getStackTrace(e), e);
        }
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<Long> deleteIntervenant(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionUtils.getStackTrace(e), e);
        }
    }
}

