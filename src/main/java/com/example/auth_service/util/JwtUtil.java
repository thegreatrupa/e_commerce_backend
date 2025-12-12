package com.example.auth_service.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Long id, String name, String emailId){
        long expiration = accessExpiration;

        return Jwts.builder()
                .claim("id", id)
                .claim("name", name)
                .claim("emailId", emailId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    public String generateRefreshToken(Long id) {
        long expiration = refreshExpiration;
        return Jwts.builder()
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return false;
        }
    }
}
