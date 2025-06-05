package com.whs.dev2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.whs.dev2.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}