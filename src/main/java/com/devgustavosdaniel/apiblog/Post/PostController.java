package com.devgustavosdaniel.apiblog.Post;

import com.devgustavosdaniel.apiblog.Author.Author;
import com.devgustavosdaniel.apiblog.Author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    public final PostService postService;
    public final PostMapper postMapper;
    public final AuthorService authorService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Validated @RequestBody PostRequestDTO postRequestDTO){
        Post createdPost = postService.create(postRequestDTO);
        PostResponseDTO postResponseDTO = postMapper.toPostResponseDTO(createdPost);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest() //Esta é uma classe utilitária do Spring Framework que ajuda a construir URIs.
                .path("/{id}")
                .buildAndExpand(postResponseDTO.idPost()) //O método buildAndExpand() finaliza a construção da URI e substitui os placeholders
                .toUri(); // converte o objeto java.net.URI
        return ResponseEntity.created(uri).body(postResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPost(){
        List<Post> allPost = postService.getAllPost();
        List<PostResponseDTO> postResponseDTOList = allPost.stream()  // Converte cada entidade Post para PostResponseDTO
                .map(postMapper ::toPostResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponseDTOList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PostResponseDTO> getPostById (@PathVariable Long id) {
        Post post = postService.postById(id);
        PostResponseDTO postResponseDTO = postMapper.toPostResponseDTO(post);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostResponseDTO>> getByTitle(@PathVariable String title) {
        List<Post> posts = postService.searchByTitle(title);
        List<PostResponseDTO> postResponseDTOSearch = posts.stream()
                .map(postMapper::toPostResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponseDTOSearch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @Validated @RequestBody PostRequestDTO postRequestDTO){
        Post postExiste = postService.postById(id);
        postExiste.setTitle(postRequestDTO.title());
        postExiste.setText(postRequestDTO.text());
        postExiste.setPublicationDate(postRequestDTO.publicationDate());
        Author author = authorService.getAuthorId(postRequestDTO.idAuthor()); // Busca o autor pelo ID vindo do DTO
        postExiste.setAuthor(author);
        Post postUpdate = postService.save(postExiste);
        PostResponseDTO postResponseDTOAtt = postMapper.toPostResponseDTO(postUpdate);
        return ResponseEntity.ok(postResponseDTOAtt);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePostPorId(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<PostResponseDTO>> getPostByAuthor(@PathVariable Long authorId){
        List<Post> postsAuthor = postService.postDoAuthor(authorId);
        List<PostResponseDTO> postResponseDTOsAuthor = postsAuthor.stream()// Converte cada entidade Post para PostResponseDTO.
                .map(postMapper ::toPostResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponseDTOsAuthor);
    }
}
