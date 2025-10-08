package com.monarch.monarch_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserDto(

        @NotBlank(message = "This field is required!")
        @Size(max = 16, message = "Name too long!")
        String name,

        @NotBlank(message = "This field is required!")
        @Size(max = 25, message = "Last name too long!")
        String lastName,

        @NotNull(message = "This field is required!")
        LocalDate birthdate,

        @NotBlank(message = "This field is required!")
        @Size(max = 100, message = "Email too long!")
        String email,

        @NotBlank(message = "This field is required!")
        @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters long!")
        String password
) {}
