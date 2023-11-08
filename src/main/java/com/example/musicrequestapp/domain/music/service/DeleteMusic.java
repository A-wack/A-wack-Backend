package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.exception.MusicNotFoundException;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMusic {
    private final MusicRepository musicRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(String url) {
        userFacade.validateAdminUser();

        Music music = musicRepository.findByUrl(url)
                .orElseThrow(() -> MusicNotFoundException.EXCEPTION);

        musicRepository.delete(music);
    }

}
