package com.kienpham.coffee.security;

import com.kienpham.coffee.entity.User;
import com.kienpham.coffee.security.db.CustomUserDetails;
import com.kienpham.coffee.util.JwtUtil;
import dto.request.AuthenticationRequest;
import dto.response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthenticationService(UserDetailsService userDetailsService,
                                 AuthenticationManager authManager,
                                 JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        String userId = userDetails.getUsername();
        boolean isLocked = !userDetails.isAccountNonLocked();
        String userName = null;
        if(userDetails instanceof CustomUserDetails){
            userName = ((CustomUserDetails) userDetails).getUser().getUserName();
        }
        User user = ((CustomUserDetails) userDetails).getUser();

        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userName(userName)
                .email(user.getUserEmail())
                .role(user.getRole())
                .fullName(user.getFullName())
                .build();
    }
}
