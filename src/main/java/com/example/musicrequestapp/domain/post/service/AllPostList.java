package com.example.musicrequestapp.domain.post.service;

import com.example.musicrequestapp.domain.music.entity.SelectEnum;
import com.example.musicrequestapp.domain.post.collection.PostCollection;
import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.repository.PostRepository;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
// 어드민 권한
public class AllPostList {
    private final PostRepository postRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<PostResponse> execute(Pageable pageable) {
        userFacade.validateAdminUser();

        List<Post> list = postRepository.findAll(pageable)
                .stream()
                .filter(post -> post.getIsSuccess().equals(SelectEnum.CHECKING.getNum())).toList();

        PostCollection postCollection = new PostCollection(list);

        return postCollection.toPostResponses();
    }

}
