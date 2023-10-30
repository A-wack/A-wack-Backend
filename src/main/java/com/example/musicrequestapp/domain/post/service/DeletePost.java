package com.example.musicrequestapp.domain.post.service;

import com.example.musicrequestapp.domain.post.Exception.UserNotMatchException;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.repository.PostRepository;
import com.example.musicrequestapp.domain.post.service.facade.PostFacade;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePost {
    private final PostRepository postRepository;
    private final UserFacade userFacade;
    private final PostFacade postFacade;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.getUser();
        Post post = postFacade.getPostById(id);

        if (post.getUser() == user) {
            postRepository.deleteById(post.getId());
        } else {
            throw UserNotMatchException.EXCEPTION;
        }
    }

}
