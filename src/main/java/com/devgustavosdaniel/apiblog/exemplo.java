


    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE para "/api/posts/{id}"
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        // Este método é chamado quando uma requisição DELETE é enviada para "/api/posts/{algumId}".
        // @PathVariable Long id extrai o ID do post a ser deletado da URL.
        postService.deletePostPorId(id); // Chama o serviço para deletar o post.
        return ResponseEntity.noContent().build(); // Retorna a resposta com status 204 (No Content), indicando sucesso sem corpo na resposta.
    }

    @GetMapping("/author/{authorId}") // Mapeia requisições HTTP GET para "/api/posts/author/{authorId}"
    public ResponseEntity<List<PostResponseDTO>> getPostsByAuthor(@PathVariable Long authorId) {
        // Este método é chamado quando uma requisição GET é enviada para "/api/posts/author/{algumId}".
        // @PathVariable Long authorId extrai o ID do autor da URL.
        List<Post> posts = postService.postDoAuthor(authorId); // Chama o serviço para buscar posts por ID do autor.
        List<PostResponseDTO> postResponseDTOs = posts.stream() // Converte cada entidade Post para PostResponseDTO.
                .map(postMapper::toPostResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponseDTOs); // Retorna a resposta com status 200 (OK) e uma lista dos posts do autor.
    }
}
