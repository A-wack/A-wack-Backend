package com.example.musicrequestapp.domain.user.entity;

import com.example.musicrequestapp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@DataJpaTest
class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void  회원가입테스트() {
        // g
        String name = "김승원";
        String email = "test@test.test";
        String password = "password";

        // w
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();

        userRepository.save(user);

        //t
        User savedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException());
        Assertions.assertNotNull(savedUser); // not null
        Assertions.assertEquals(name, savedUser.getName()); // 이름
        Assertions.assertEquals(email, savedUser.getEmail()); // 이메일
        Assertions.assertEquals(password, savedUser.getPassword()); // 비밀번호
    }
}