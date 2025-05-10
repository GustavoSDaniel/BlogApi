package com.devgustavosdaniel.apiblog.dto;

import jakarta.validation.constraints.NotBlank;

public record PostDTO (
        @NotBlank(message = "Campo obrigatório")
        String title,
        @NotBlank(message = "Campo obrigatório")
        String text,
        @NotBlank(message = "Campo obrigatório")
        Long idAuthor) 
{
}
