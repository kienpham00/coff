package com.kienpham.coffee.controller.sercurity;


import com.kienpham.coffee.security.AuthenticationService;
import com.kienpham.coffee.service.UserService;
import dto.BaseResponse;
import dto.request.AuthenticationRequest;
import dto.response.AuthenticationResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }
    @PostMapping("/p/authenticate")
    public BaseResponse authenticate(@RequestBody AuthenticationRequest request){
        AuthenticationResponse response = authenticationService.authenticate(request);
        return BaseResponse.builder().success(true).data(response).build();
    }

    @PostMapping("/p/forgotPass/{userName}")
    public BaseResponse forgotPass(@PathVariable String userName) throws MessagingException {
        return userService.updatePass(userName);
    }

    public static void main(String[] args) {
        String pass = "kien123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(pass));
    }
}
