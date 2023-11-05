package com.example.musicrequestapp.domain.sse.service;

import com.example.musicrequestapp.domain.music.entity.Music;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor
public class SseService {
    private final UserFacade userFacade;

    private final ConcurrentMap<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Async("sendEvent")
    public void sendEvent(final Music music) {
        this.emitters.forEach((userId, emitter) -> {
            try {
                emitter.send(SseEmitter.event()
                        .name("기상송")
                        .data(music, MediaType.APPLICATION_JSON));
            } catch (final IOException e) {
                emitter.complete();
                emitters.remove(userId);
            }
        });
    }

    public SseEmitter subscribe() throws IOException {
        User user = userFacade.getUser();
        Long userId = user.getId();

        final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> this.emitters.remove(userId));
        emitter.onTimeout(() -> this.emitters.remove(userId));

        emitters.put(userId, emitter);

        emitter.send(SseEmitter.event()
                .name("SSE connect")
                .data("SSE connect!"));

        return emitter;
    }

}
