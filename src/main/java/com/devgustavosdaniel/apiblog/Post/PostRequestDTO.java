package com.devgustavosdaniel.apiblog.Post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PostRequestDTO(
        @NotBlank(message = "Campo obrigatório") // só pode usar essa anotação em string
        @Size(min = 3, max = 60)
        String title,
        @NotBlank(message = "Campo obrigatório")
        String text,
        @Past(message = "Não pode ser uma data futura")
        @Schema(description = "Data de publicação")
        @NotNull(message = "Campo obrigatório")
        LocalDate  publicationDate,
        @NotNull(message = "Campo obrigatório")
        Long idAuthor) 
{ }
