package com.example.musicrequestapp.domain.post.service;

import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCleanUp {
    private final PostRepository postRepository;

    @Transactional
    @Scheduled(cron = "0 0 5 L * ?", zone = "Asia/Seoul")
    public void run() {
        List<Post> list = postRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Post post : list) {
            LocalDateTime uploadDateTime = post.getUpload();
            Duration duration = Duration.between(uploadDateTime, now);

            if (duration.toDays() >= 7) {
                postRepository.delete(post);
            }
        }
    }

}
