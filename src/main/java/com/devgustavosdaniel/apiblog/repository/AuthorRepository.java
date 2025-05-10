package com.devgustavosdaniel.apiblog.repository;

import com.devgustavosdaniel.apiblog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
