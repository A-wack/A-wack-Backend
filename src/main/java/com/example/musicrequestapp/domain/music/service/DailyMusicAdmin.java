package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicAdminResponse;
import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyMusicAdmin {
    private final MusicRepository musicRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<DailyMusicAdminResponse> execute() {
        userFacade.validateAdminUser();

        List<Music> musicList = musicRepository.findAll();

        return musicList.stream()
                .map(DailyMusicAdminResponse::new)
                .toList();
    }

}
