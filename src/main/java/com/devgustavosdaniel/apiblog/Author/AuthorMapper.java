package com.devgustavosdaniel.apiblog.Author;

import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

public AuthorResponseDTO toAuthorResponseDTO(Author author) {
    if (author == null){
        return null;
    }
    return new AuthorResponseDTO(
            author.getIdAuthor(),
            author.getName()
    );
}

}
