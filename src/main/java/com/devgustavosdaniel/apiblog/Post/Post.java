package com.devgustavosdaniel.apiblog.Post;

import com.devgustavosdaniel.apiblog.Author.Author;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "idAuthor")
    private Author author;

    public Post() {
    }

    public Post(Author author, String text, String title) {
        this.author = author;
        this.text = text;
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getIdPost() {
        return idPost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
