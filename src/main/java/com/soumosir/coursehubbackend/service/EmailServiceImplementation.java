package com.soumosir.coursehubbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class EmailServiceImplementation implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

   @Override
   public void sendEmail(String username,String email,String emailDisplayText){
       try {

           MimeMessage message = emailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message);
           helper.setFrom("soumosirmarylandd@gmail.com", "Course Hub Support");
           helper.setTo(email);

           String subject = "Otp to reset your password";

           String content = "<p>Hello, "+username+"</p>"
                   + "<p>"+emailDisplayText+"</p>";

           helper.setSubject(subject);

           helper.setText(content, true);

           emailSender.send(message);
           log.info("Email sent to {} ",email);

       } catch (MessagingException | UnsupportedEncodingException e) {
           log.error("Unable to send mail", HttpStatus.UNPROCESSABLE_ENTITY);
           throw new RuntimeException(e);
       }

   }

}
