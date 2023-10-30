package com.example.musicrequestapp.domain.post.service;

import com.example.musicrequestapp.domain.post.collection.PostCollection;
import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.service.facade.PostFacade;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPostList {
    private final PostFacade postFacade;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<PostResponse> execute() {
        User user = userFacade.getUser();
        Long userId = user.getId();

        List<Post> list = postFacade.getPostListByUser(userId);
        PostCollection postCollection = new PostCollection(list);

        return postCollection.toPostResponses();
    }

}
