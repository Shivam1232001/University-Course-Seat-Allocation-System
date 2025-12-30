package com.university.seat.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12";

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}

