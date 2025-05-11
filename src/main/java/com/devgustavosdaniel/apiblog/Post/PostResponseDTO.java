package com.devgustavosdaniel.apiblog.Post;
import java.time.LocalDate;

public record PostResponseDTO(
        Long idPost,
        String title,
        String text,
        LocalDate publicationDate,
        Long idAutor

) {
}
