package com.kienpham.coffee.security;

import com.kienpham.coffee.repository.UserSql;
import dto.BaseResponse;
import dto.request.AuthenticationRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserSql mUser;

    public AuthenticationService( UserSql mUser) {
        this.mUser = mUser;
    }
    public BaseResponse verification(AuthenticationRequest objLogin){

        boolean result = mUser.getUser(objLogin);
        if(result){
            return BaseResponse
                    .builder()
                    .success(true)
                    .message("Login Successfully!")
                    .build();
        }
        return BaseResponse
                .builder()
                .success(false)
                .message("Login Failed!")
                .build();

    }
}
