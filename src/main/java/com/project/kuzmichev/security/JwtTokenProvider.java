package com.project.kuzmichev.security;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret-token}")
    private String secretToken;
    @Value("${jwt.expiration-time-ms}")
    private Long expirationTimeMs;

    private final static String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationTimeMs))
                .signWith(SignatureAlgorithm.HS256, secretToken)
                .compact();
    }
    public boolean isValidToken(String token) {
        if(token == null) return false;
        try {
            Jwts.parser().setSigningKey(secretToken).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
        }
        return false;
    }

    public String parseToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.replaceFirst(TOKEN_PREFIX, "");
        }
        return null;
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretToken).parseClaimsJws(token).getBody().getSubject();
    }
}
