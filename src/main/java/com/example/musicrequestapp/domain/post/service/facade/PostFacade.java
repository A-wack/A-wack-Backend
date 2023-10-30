package com.example.musicrequestapp.domain.post.service.facade;

import com.example.musicrequestapp.domain.post.Exception.PostNotFoundException;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }

    public List<Post> getPostListByUser(Long id) {
        return postRepository.findByUser_IdOrderByUpload(id);
    }


}
