package com.monarch.monarch_api.dto;

public record UserDto(

        String name,

        String lastName,

        Integer birthdate,

        String email,

        String password
) {}
