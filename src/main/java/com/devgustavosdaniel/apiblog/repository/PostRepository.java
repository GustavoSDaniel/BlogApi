package com.devgustavosdaniel.apiblog.repository;

import com.devgustavosdaniel.apiblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
