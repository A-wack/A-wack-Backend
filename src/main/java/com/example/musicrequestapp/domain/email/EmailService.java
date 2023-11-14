package com.example.musicrequestapp.domain.email;

import com.example.musicrequestapp.domain.auth.exception.AlreadyEmailExistException;
import com.example.musicrequestapp.domain.email.entity.UserEmail;
import com.example.musicrequestapp.domain.email.repository.UserEmailRepository;
import com.example.musicrequestapp.domain.email.controller.dao.AuthCodeDao;
import com.example.musicrequestapp.domain.email.controller.dto.EmailRequest;
import com.example.musicrequestapp.domain.email.controller.dto.EmailVerifyResponse;
import com.example.musicrequestapp.domain.email.exception.MailSendException;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.repository.UserRepository;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final AuthCodeDao authCodeDao;
    private final UserRepository userRepository;
    private final UserEmailRepository userEmailRepository;
    private final JavaMailSender mailSender;

    @Value("${AdminMail.id}")
    private String adminMail;

    private static final Random random = new Random();

    private MimeMessage sendEmailForAuth(String to) throws MessagingException, UnsupportedEncodingException {
        String authCode = getNewAuthCode();
        log.info(authCode);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("DSM 메일 인증");

        String htmlBody = getEmailTemplateForAuth(authCode);

        helper.setText(htmlBody, true);
        helper.setFrom(new InternetAddress(adminMail, "DSM-mail-verify"));

        authCodeDao.saveAuthCode(to, authCode);

        return message;
    }

    private String getEmailTemplateForAuth(String authCode) {
        return "<div style='margin: 10px; background-color: #f5f5f5; padding: 20px; border-radius: 10px;'>"
                + "<p style='font-size: 16px; color: #333;'><b><span style='color: #007bff;'>D</span><span style='color: #ffcc00;'>S</span><span style='color: #ff0000;'>M</span></b> 이메일 인증 코드 :</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #007bff; letter-spacing: 3px;'>" + authCode + "</p>"
                + "<p style='font-size: 14;font-style: italic; color: #999;'>인증 코드는 3시간 동안 유효합니다.</p>"
                + "</div>";
    }

    @Async
    public void sendAuthCode(EmailRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.email();

        emailIsExist(email);

        MimeMessage message = sendEmailForAuth(email);

        sendSimpleMessage(message);
    }

    private void emailIsExist(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyEmailExistException.EXCEPTION;
        }
    }

    private void sendSimpleMessage(MimeMessage message) {
        try{
            mailSender.send(message);
        }catch(MailException e){
            throw MailSendException.EXCEPTION;
        }
    }

    private String getNewAuthCode() {
        StringBuilder authCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            authCode.append(random.nextInt(10));
        }

        return authCode.toString();
    }

    @Transactional
    public EmailVerifyResponse verifyEmail(String authCode, EmailRequest request) {
        String email = request.email();
        if (isVerify(authCode, email)) {
            authCodeDao.deleteAuthCode(email);

            userEmailRepository.save(UserEmail.builder()
                    .email(email)
                    .build());
            return createResponse("인증 성공", true);
        } else {
            return createResponse("인증 실패", false);
        }
    }

    private EmailVerifyResponse createResponse(String message, Boolean type) {
        return EmailVerifyResponse.builder()
                .message(message)
                .isSuccess(type)
                .build();
    }

    private boolean isVerify(String authCode, String email) {
        return (authCodeDao.hasKey(email) &&
                authCodeDao.getAuthCode(email)
                        .equals(authCode));
    }

}