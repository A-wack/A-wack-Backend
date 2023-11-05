package com.example.musicrequestapp.domain.sse.controller;

import com.example.musicrequestapp.domain.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SseController {
    private final SseService sseService;

    @GetMapping(value = "/connect",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect() throws IOException {
//        CacheControl cacheControl = CacheControl.noCache();
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setCacheControl(cacheControl.getHeaderValue());
//        headers.set("Connection", "keep-alive");

        return sseService.subscribe();
    }

}
