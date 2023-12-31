package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.controller.dto.SelectRequest;
import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.entity.SelectEnum;
import com.example.musicrequestapp.domain.music.exception.AlreadyUsedPostException;
import com.example.musicrequestapp.domain.music.exception.UnableToAddMusicException;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.service.facade.PostFacade;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
//어드민 권한
public class SelectMusic {
    private final MusicRepository musicRepository;
    private final UserFacade userFacade;
    private final PostFacade postFacade;

    private static final String SELECTED = SelectEnum.SELECTED.getNum();
    private static final String DENIED = SelectEnum.NOT_SELECTED.getNum();

    @Transactional
    public void execute(SelectRequest request) {
        userFacade.validateAdminUser();
        validateMusicCount();
        Post post = postFacade.getPostById(request.postId());
        validatePostStatus(post);

        if (Boolean.TRUE.equals(request.isSuccess())) {
            addMusicAndSendEvent(post);
        } else {
            post.isSelected(DENIED);
        }
    }

    private void validateMusicCount() {
        if (musicRepository.count() == 2) {
            throw UnableToAddMusicException.EXCEPTION;
        }
    }

    private void validatePostStatus(Post post) {
        if (Objects.equals(post.getIsSuccess(), SELECTED) || Objects.equals(post.getIsSuccess(), DENIED)) {
            throw AlreadyUsedPostException.EXCEPTION;
        }
    }

    private void addMusicAndSendEvent(Post post) {
        Music music = Music.builder()
                .url(post.getContent())
                .user(post.getTitle())
                .build();

        musicRepository.save(music);
        post.isSelected(SELECTED);

    }


}
