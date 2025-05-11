package com.devgustavosdaniel.apiblog.service;

import com.devgustavosdaniel.apiblog.exceptions.MyException;
import com.devgustavosdaniel.apiblog.model.Post;
import com.devgustavosdaniel.apiblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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

}
