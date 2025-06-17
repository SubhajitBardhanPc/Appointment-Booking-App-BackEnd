package com.Subhajit.ApointmentBooking.App.JwtServices;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class jwtToken {
    private static  final Key key  = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static  String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("AppointmentBookingApp")
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(key)
                .compact();
    }
    public static boolean validationToken(String token,String username){
        try{
            String tokenUser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return tokenUser.equals(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
