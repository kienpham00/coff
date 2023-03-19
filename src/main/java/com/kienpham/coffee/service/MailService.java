package com.kienpham.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendMail(String email,String pass) throws MessagingException {
        String subject = "Thông báo thay thay đổi mật khẩu";
        String senderName = "Coffee Sys";
        String mailContents = "<b>Thông báo thay đổi mật khẩu!</b> <br/>";
        mailContents+=
                " Mật khẩu mới của bạn là:<b> <span style='color:red'>"+ pass+"</span></b><br/>" ;
        mailContents+="<b><i>Coffee Sys</i></b>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,false,"UTF-8");
        try {
            helper.setFrom("Rice-service", senderName);
            helper.setSubject(subject);
            helper.setTo(email);
            helper.setText(mailContents,true);
            mailSender.send(message);
            System.out.println("Email is just sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
