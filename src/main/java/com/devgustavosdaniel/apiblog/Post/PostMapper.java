package com.devgustavosdaniel.apiblog.Post;

import com.devgustavosdaniel.apiblog.exceptions.MyException;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostResponseDTO toPostResponseDTO(Post post) {
        if (post == null){
            return null;
        }
        return new PostResponseDTO(
                post.getIdPost(),
                post.getTitle(),
                post.getText(),
                post.getPublicationDate(),
                post.getAuthor() != null ? post.getAuthor().getIdAuthor() : null // condição ? valor_se_verdadeiro : valor_se_falso

    );
    }
}
