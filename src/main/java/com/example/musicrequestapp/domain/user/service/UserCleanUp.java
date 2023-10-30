package com.example.musicrequestapp.domain.user.service;

import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCleanUp {
    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 1 * * MON", zone = "Asia/Seoul")
    public void run() {
        List<User> deleteUserList = userRepository.findByRole(Role.ROLE_GUEST);
        userRepository.deleteAll(deleteUserList);
    }

}
