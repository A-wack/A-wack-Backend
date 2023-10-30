package com.example.musicrequestapp.domain.auth.service;

import com.example.musicrequestapp.domain.auth.controller.dto.request.SignUpRequest;
import com.example.musicrequestapp.domain.auth.exception.AlreadyEmailExistException;
import com.example.musicrequestapp.domain.auth.exception.AlreadyNameExistException;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(SignUpRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw AlreadyEmailExistException.EXCEPTION;
        }

        if (userRepository.findByName(request.getName()).isPresent()) {
            throw AlreadyNameExistException.EXCEPTION;
        }

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(password)
                .build();

        userRepository.save(user);

    }

}
