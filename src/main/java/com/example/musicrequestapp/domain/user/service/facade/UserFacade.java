package com.example.musicrequestapp.domain.user.service.facade;

import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.exception.UserNotFoundException;
import com.example.musicrequestapp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}