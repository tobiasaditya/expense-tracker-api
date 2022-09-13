package com.obider.expensetrackerapi.security;

import com.obider.expensetrackerapi.Constants;
import com.obider.expensetrackerapi.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Security {
    public String generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp+Constants.TOKEN_VALIDITY))
                .claim("userId",user.getId())
                .compact();
        return token;
    }

}
