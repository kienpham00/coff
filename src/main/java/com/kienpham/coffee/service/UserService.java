package com.kienpham.coffee.service;

import com.kienpham.coffee.entity.SendMailEvent;
import com.kienpham.coffee.entity.User;
import com.kienpham.coffee.repository.UserRepository;
import com.kienpham.coffee.security.db.CustomUserDetails;
import dto.BaseResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final ApplicationEventPublisher publisher;

    public UserService(UserRepository userRepository, MailService mailService, ApplicationEventPublisher publisher) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.publisher = publisher;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser =  userRepository.findUserByUserName(username);
        if(optUser.isEmpty()) throw new UsernameNotFoundException("User Not Found");
        return new CustomUserDetails(optUser.get());
    }

    public BaseResponse updatePass(String userName) throws MessagingException {
        Optional<User> optUser =  userRepository.findUserByUserName(userName);
        if(optUser.isEmpty())
            return BaseResponse.builder().success(false).message("Username is invalid").build();
        User user = optUser.get();
        String email = user.getUserEmail();
        StringBuilder sb = new StringBuilder(email);
        for (int i = 3; i < sb.length() && sb.charAt(i) != '@'; ++i) {
            sb.setCharAt(i, '*');
        }
        email = sb.toString();

        String newPass = genNewPass();
        String passString = newPass;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassWord(encoder.encode(newPass));
        userRepository.save(user);
        mailService.sendMail(user.getUserEmail(),passString);
        return BaseResponse.builder()
                .success(true)
                .message("New password jut sent to: "+email)
                .data(email)
                .build();
    }
    private String genNewPass(){
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%")).toCharArray();
        String randomStr = RandomStringUtils.random( 9, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        return randomStr;
    }
}
