package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.controller.dto.SelectRequest;
import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.entity.SelectEnum;
import com.example.musicrequestapp.domain.music.exception.AlreadyUsedPostException;
import com.example.musicrequestapp.domain.music.exception.UnableToAddMusicException;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.service.facade.PostFacade;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
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
        User user = userFacade.getUser();

        if (user.getRole() != Role.ROLE_ADMIN) {
            throw NoPermissionException.EXCEPTION;
        }

        if (musicRepository.count() == 2) {
            throw UnableToAddMusicException.EXCEPTION;
        }

        Post post = postFacade.getPostById(request.getPostId());

        if (Objects.equals(post.getIsSuccess(), SELECTED) ||
            Objects.equals(post.getIsSuccess(), DENIED)) {
            throw AlreadyUsedPostException.EXCEPTION;
        }

        if (Boolean.TRUE.equals(request.getIsSuccess())) {
            Music music = Music.builder()
                    .url(post.getContent())
                    .user(post.getTitle())
                    .build();

            musicRepository.save(music);
            post.isSelected(SELECTED);
        } else {
            post.isSelected(DENIED);
        }

    }

}
