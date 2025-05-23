package com.devgustavosdaniel.apiblog.Post;

import com.devgustavosdaniel.apiblog.Author.Author;
import com.devgustavosdaniel.apiblog.Author.AuthorRepository;
import com.devgustavosdaniel.apiblog.exceptions.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public Post create(PostRequestDTO postRequestDTO){
        Author author = authorRepository.findById(postRequestDTO.idAuthor())
                .orElseThrow(() -> new MyException("Id do Autor "+postRequestDTO.idAuthor()+ " não encontrado"));
        Post newPost = new Post();
        newPost.setTitle(postRequestDTO.title());
        newPost.setText(postRequestDTO.text());
        newPost.setPublicationDate(postRequestDTO.publicationDate());
        newPost.setAuthor(author);

        return postRepository.save(newPost);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post postById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new MyException("Post com o ID " + id + " não encontrado"));
    }

    public List<Post> searchByTitle(String title){
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
        if (posts.isEmpty()){
            throw new MyException("Titulo não encontrado");
        }
        return posts;
    }

    public void updateById(Post post) {
        if (post.getIdPost() == null){
            throw new MyException("Não é possivel atualizar post sem ID");
        }
        if (!postRepository.existsById(post.getIdPost())) {
            throw new MyException("Post com o ID " +post.getIdPost()+ " não encontrado");
        }
        postRepository.save(post);
    }

    public void deletePostPorId(Long id){
        if (!postRepository.existsById(id)){
            throw new MyException("Post com o ID" +id+ " não encontrado");
        }
        postRepository.deleteById(id);
    }

    public List<Post> postDoAuthor(Long idAuthor) {
        if (!postRepository.existsById(idAuthor)){
            throw new MyException("Ai do Autor "+idAuthor+" não encontrado");
        }

        List<Post> postsAuthor = postRepository.findByAuthorId(idAuthor);

        if (postsAuthor.isEmpty()){
            throw new MyException("Autor não tem nenhum post vinculado");
        }

        return postsAuthor;
    }

}
