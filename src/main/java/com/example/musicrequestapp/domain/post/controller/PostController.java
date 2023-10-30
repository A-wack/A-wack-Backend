package com.example.musicrequestapp.domain.post.controller;

import com.example.musicrequestapp.domain.post.controller.dto.request.PostRequest;
import com.example.musicrequestapp.domain.post.service.DeletePost;
import com.example.musicrequestapp.domain.post.service.UploadPost;
import com.example.musicrequestapp.global.error.exception.TooManyRequestException;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final UploadPost uploadPost;
    private final DeletePost deletePost;
    private final Bucket bucket;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setUploadPost(@Valid @RequestBody PostRequest request) {
        if (bucket.tryConsume(1)) {
            uploadPost.execute(request);
        } else {
            throw TooManyRequestException.EXCEPTION;
        }
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void setDeletePost(@PathVariable Long postId) {
        deletePost.execute(postId);
    }

}
