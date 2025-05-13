package com.devgustavosdaniel.apiblog.Author;

import com.devgustavosdaniel.apiblog.Post.PostRequestDTO;
import com.devgustavosdaniel.apiblog.Post.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthor(AuthorResponseDTO authorResponseDTO){
        List<Author> allAuthor = authorService.getAllAuthors();
        List<AuthorResponseDTO> authorResponseDTOList = allAuthor.stream() // Converte cada entidade Post para PostResponseDTO
                .map(authorMapper ::toAuthorResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorResponseDTOList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        Author authorId = authorService.getAuthorId(id);
        AuthorResponseDTO authorResponseDTOId = authorMapper.toAuthorResponseDTO(authorId);
        return ResponseEntity.ok(authorResponseDTOId);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<AuthorResponseDTO>> getByNameAuthor(@PathVariable String name){
        List<Author> authorName = authorService.getNameAuthor(name);
        List<AuthorResponseDTO> authorResponseDTOName = authorName.stream()
                .map(authorMapper ::toAuthorResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorResponseDTOName);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @Validated @RequestBody AuthorRequestDTO authorRequestDTO){
        Author authorExiste = authorService.getAuthorId(id);
        authorExiste.setName(authorRequestDTO.name());
        Author updateAuthor = authorService.saveAuthor(authorExiste);
        AuthorResponseDTO authorResponseDTOUpdate = authorMapper.toAuthorResponseDTO(updateAuthor);
        return ResponseEntity.ok(authorResponseDTOUpdate);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
