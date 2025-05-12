package com.devgustavosdaniel.apiblog.Author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    public final AuthorService authorService;
    public final AuthorMapper authorMapper;

    @PostMapping()
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Validated @RequestBody AuthorRequestDTO authorRequestDTO){
        Author createAuthor = authorService.create(authorRequestDTO);
        AuthorResponseDTO authorResponseDTO = authorMapper.toAuthorResponseDTO(createAuthor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(authorResponseDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(authorResponseDTO);

    }


}
