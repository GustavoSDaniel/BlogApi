package com.devgustavosdaniel.apiblog.Author;

import jakarta.validation.constraints.NotBlank;


public record AuthorRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        String name)
{
}
