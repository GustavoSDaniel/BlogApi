package com.devgustavosdaniel.apiblog.Author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AuthorDTO(
        @NotBlank(message = "Campo obrigatório")
        String name,
        @NotBlank(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        @Schema(description = "Data de publicação")
        LocalDate publicationDate)
{
}
