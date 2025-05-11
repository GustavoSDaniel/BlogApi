package com.devgustavosdaniel.apiblog.Author;

import com.devgustavosdaniel.apiblog.Post.Post;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    public Author() {
    }

    public Author( String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
    }

    public Long getIdAuthor() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
