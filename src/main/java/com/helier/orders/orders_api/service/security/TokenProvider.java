package com.helier.orders.orders_api.service.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class TokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long expirationTime;

    private Key key;

    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        jwtParser = Jwts
          .parserBuilder()
          .setSigningKey(key)
          .build();
    }

    public String createAccessToken(Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getAuthority();

        return Jwts
        .builder()
        .setSubject(authentication.getName())
        .claim("role", role)
        .signWith(key, SignatureAlgorithm.HS512)
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
        .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        String role = claims.get("role").toString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
