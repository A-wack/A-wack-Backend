package com.example.musicrequestapp.domain.auth.service;

import com.example.musicrequestapp.domain.auth.controller.dto.request.SignUpRequest;
import com.example.musicrequestapp.domain.auth.exception.AlreadyEmailExistException;
import com.example.musicrequestapp.domain.auth.exception.AlreadyNameExistException;
import com.example.musicrequestapp.domain.email.repository.UserEmailRepository;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.repository.UserRepository;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEmailRepository userEmailRepository;

    @Transactional
    public void execute(SignUpRequest request) {
        validateRequest(request.name());

        String email = request.email();
        validateEmail(email);

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .name(request.name())
                .email(email)
                .password(encodedPassword)
                .role(Role.ROLE_STUDENT)
                .build();

        userRepository.save(user);
        deleteEmailFromRedis(email);
    }

    private void validateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyEmailExistException.EXCEPTION;
        }
        if (userEmailRepository.findById(email).isEmpty()) {
            throw NoPermissionException.EXCEPTION;
        }
    }

    private void validateRequest(String name) {
        if (userRepository.findByName(name).isPresent()) {
            throw AlreadyNameExistException.EXCEPTION;
        }
    }

    private void deleteEmailFromRedis(String email) {
        userEmailRepository.deleteById(email);
    }

}
