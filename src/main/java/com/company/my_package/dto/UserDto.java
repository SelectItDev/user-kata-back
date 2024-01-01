package com.company.my_package.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(Long id, String firstName, String lastName, String email, LocalDateTime creationDate, LocalDateTime updateDate) {

    public UserDto(String firstName, String lastName, String email) {
        this(null, firstName, lastName, email, null, null);
    }
}
