package com.devgustavosdaniel.apiblog.repository;

import com.devgustavosdaniel.apiblog.model.Author;
import com.devgustavosdaniel.apiblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingIgnoreCase(String name);

}
