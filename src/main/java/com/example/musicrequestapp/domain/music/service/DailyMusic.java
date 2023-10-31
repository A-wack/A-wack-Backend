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

@Service
@RequiredArgsConstructor
public class DailyMusic {
    private final MusicRepository musicRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<DailyMusicResponse> execute() {

        User user = userFacade.getUser();

        if (user.getRole() == Role.ROLE_GUEST) {
            throw NoPermissionException.EXCEPTION;
        }

        long cnt = musicRepository.count();

        if (cnt == 0) {
            DailyMusicResponse emptyResponse = createEmptyResponse();
            return Collections.nCopies(2, emptyResponse);
        }

        List<Music> list = musicRepository.findAll();

        if (cnt == 1) {
            DailyMusicResponse emptyResponse = createEmptyResponse();

            List<DailyMusicResponse> dailyMusicResponses = new java.util.ArrayList<>(
                    list.stream()
                            .map(DailyMusicResponse::new)
                            .toList());

            dailyMusicResponses.add(emptyResponse);
            return dailyMusicResponses;
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
