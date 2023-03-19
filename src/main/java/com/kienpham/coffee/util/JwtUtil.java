package com.kienpham.coffee.util;


import com.kienpham.coffee.entity.User;
import com.kienpham.coffee.security.db.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
//    private final String KEY = "something";
    @Autowired
    PrivateKey privateKey;

    public String generateToken(UserDetails validUser){
        //add something that y want
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,validUser.getUsername(),60*8);
    }

    private String createToken(Map<String, Object> claims, String username,long timeMinutes) {
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*timeMinutes))
                .signWith(SignatureAlgorithm.RS256,privateKey).compact();
    }
    public String generateRefreshToken(UserDetails validUser){
        Map<String,Object> claims = new HashMap<>();
        claims.put("type",1);
        return createToken(claims,validUser.getUsername(),10*60);
    }

    public boolean validateToken(String token, UserDetails user) {
        String userName = getUserName(token);
        return(userName.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public String getUserName(String token) {
        return getClaim(getAllClaims(token), Claims::getSubject);
    }
    public int getType(String token){
        return 1;
    }
    public boolean isLockedUser(String token){
        return false;
    }

    public Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
    public Date getExpiration(String token){
        return getClaim(getAllClaims(token), Claims::getExpiration);
    }



    public Claims getAllClaims(String token){
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody();
    }
    public <T> T getClaim(Claims claims, Function<Claims,T> resolver){
        return resolver.apply(claims);
    }
}
