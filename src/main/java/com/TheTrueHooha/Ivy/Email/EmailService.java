package com.TheTrueHooha.Ivy.Email;

//service class contain the brain to send the email to confirm registration

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor

public class EmailService implements EmailSender {

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String emailAddress) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(emailAddress, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("confirm your email");
            mimeMessageHelper.setFrom("community@ivy.com");
        } catch (MessagingException e) {
            logger.error("failed", e);
            throw new IllegalStateException("failed to send");
        }

    }
}
