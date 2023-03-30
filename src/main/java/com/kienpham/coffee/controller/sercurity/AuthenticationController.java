package com.kienpham.coffee.controller.sercurity;


import com.kienpham.coffee.security.AuthenticationService;
import com.kienpham.coffee.service.UserService;
import dto.BaseResponse;
import dto.request.AuthenticationRequest;
import dto.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    SmartValidator validator;
    private final AuthenticationService cAuthentication;
    private final UserService userService;

    public AuthenticationController(AuthenticationService cAuthentication, UserService userService) {
        this.cAuthentication = cAuthentication;
        this.userService = userService;
    }
    @PostMapping("/p/authenticate")
    public BaseResponse doLogin(@RequestBody AuthenticationRequest objLogin ,BindingResult result){
        validator.validate(objLogin, result);
        if (result.hasErrors()) {
            return BaseResponse.builder().success(false).message("Invalid Data").build();
        }
        BaseResponse response = cAuthentication.verification(objLogin);

        return response;
//        return BaseResponse.builder().success(true).data(response).build();
    }

    @PostMapping("/p/forgotPass/{userName}")
    public BaseResponse forgotPass(@PathVariable String userName) throws MessagingException {
        return userService.updatePass(userName);
    }

    public static void main(String[] args) {
        String pass = "1";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(pass));
    }
}
