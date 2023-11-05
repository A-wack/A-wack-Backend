package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicResponse;
import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyMusic {
    private final MusicRepository musicRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<DailyMusicResponse> execute() {
        userFacade.validateAdminUser();

        List<Music> list = musicRepository.findAll();
        long cnt = list.size();

        if (cnt <= 1) {
            List<DailyMusicResponse> responses = list.stream()
                    .map(DailyMusicResponse::new)
                    .collect(Collectors.toList());

            DailyMusicResponse emptyResponse = createEmptyResponse();
            responses.addAll(Collections.nCopies((int) (2 - cnt), emptyResponse));

            return responses;
        }

        return list.stream()
                .map(DailyMusicResponse::new)
                .toList();
    }

    private DailyMusicResponse createEmptyResponse() {
        return DailyMusicResponse.builder()
                .music("현재 기상송이 없습니다.")
                .build();
    }

}
