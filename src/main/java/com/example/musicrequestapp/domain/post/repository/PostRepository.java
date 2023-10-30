package com.example.musicrequestapp.domain.post.repository;

import com.example.musicrequestapp.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_IdOrderByUpload(Long id);

    List<Post> findAllByIsSuccess(String message);
}