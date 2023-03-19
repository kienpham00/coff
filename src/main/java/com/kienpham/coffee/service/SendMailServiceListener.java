package com.kienpham.coffee.service;

import com.kienpham.coffee.entity.SendMailEvent;
import com.kienpham.coffee.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendMailServiceListener {
    @Autowired
    private MailService mailService;
    @Async
    @EventListener
    public void sendMail(SendMailEvent event){
        try {
            if(event.getData() instanceof User){
                User im = (User) event.getData();
                mailService.sendMail(im.getUserEmail(),im.getPassWord());
            }
        }catch (Exception ex){
            log.error("Send Mail error!");
            ex.printStackTrace();
        }
    }
}
