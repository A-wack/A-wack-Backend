package com.example.musicrequestapp.domain.post.entity;

import com.example.musicrequestapp.domain.music.entity.SelectEnum;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column(nullable = false,
            length = 128)
    private String title;

    @Column(nullable = false,
            length = 128)
    private String content;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime upload;

    @Column(name = "is_success",
            nullable = false)
    @Builder.Default
    private String isSuccess = SelectEnum.CHECKING.getNum();

    public void isSelected(String message) {
        this.isSuccess = message;
    }

}
