package com.university.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12";
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✔ Generate JWT
    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✔ Existing method (keep as-is)
    public static Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // ✅ NEW: Boolean validation (for gRPC)
    public static boolean isTokenValid(String token) {
        try {
            validateToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ NEW: Extract role from token
    public static String extractRole(String token) {
        return validateToken(token)
                .getBody()
                .get("role", String.class);
    }
}
