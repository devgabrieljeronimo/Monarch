package com.monarch.monarch_api.dto;

/* Dto temporario para iniciar testes da api,
    atualizar utilizando Spring Security assim que possivel */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "This field is required!")
        @Size(max = 100, message = "Email too long!")
        String email,

        @NotBlank(message = "This field is required!")
        @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters long!")
        String password
) {}