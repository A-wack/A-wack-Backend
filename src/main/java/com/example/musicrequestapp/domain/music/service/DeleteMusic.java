package com.example.musicrequestapp.domain.music.service;

import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.music.exception.MusicNotFoundException;
import com.example.musicrequestapp.domain.music.repository.MusicRepository;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
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
        User user = userFacade.getUser();

        if (user.getRole() == Role.ROLE_ADMIN) {
            throw NoPermissionException.EXCEPTION;
        }

        Music music = musicRepository.findByUrl(url)
                .orElseThrow(() -> MusicNotFoundException.EXCEPTION);

        musicRepository.delete(music);
    }

}
