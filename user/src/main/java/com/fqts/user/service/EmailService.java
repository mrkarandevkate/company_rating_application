package com.fqts.user.service;

import com.fqts.user.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(Email em) {
        SimpleMailMessage s = new SimpleMailMessage();
        s.setFrom(sender);
        s.setTo(em.getEmail_address());
        s.setSubject(em.getSubject());
        s.setText(em.getMessage_body());
        mailSender.send(s);
        return "Email Sent Successfully";
    }
}
