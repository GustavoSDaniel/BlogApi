package com.devgustavosdaniel.apiblog.Post;

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

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById (Long id) {
        Post post = postService.postById(id);
        PostResponseDTO postResponseDTO = postMapper.toPostResponseDTO(post);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDTO>> getByTitle(@RequestParam String title) {
        List<Post> posts = postService.searchByTitle(title);
        List<PostResponseDTO> postResponseDTOSearch = posts.stream()
                .map(postMapper::toPostResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponseDTOSearch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @Validated @RequestBody PostResponseDTO postResponseDTO){
        Post postUpdate = new Post();
        postUpdate.setAuthor(postUpdate.getAuthor());
        postUpdate.setTitle(postUpdate.getTitle());
        postUpdate.setText(postUpdate.getText());
        postUpdate.setPublicationDate(postUpdate.getPublicationDate());
        postService.updateById(postUpdate);
        PostResponseDTO postResponseDTOAtt = postMapper.toPostResponseDTO(postUpdate);
        return ResponseEntity.ok(postResponseDTOAtt);
    }

}
