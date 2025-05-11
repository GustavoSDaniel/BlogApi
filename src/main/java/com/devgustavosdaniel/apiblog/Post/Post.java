package com.devgustavosdaniel.apiblog.Post;

import com.devgustavosdaniel.apiblog.Author.Author;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String title;
    private String text;
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name = "idAuthor")
    private Author author;

    public Post() {
    }

    public Post(String title, String text, LocalDate publicationDate, Author author) {
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getIdPost() {
        return idPost;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
