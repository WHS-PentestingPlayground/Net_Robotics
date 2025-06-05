package com.whs.dev2.jwt;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;//서명에 사용되는 비밀키
    private final long expiration;//토큰 만료 시간(ms단위)

    //생성자(applicatin.properites 설정값 주입 받기)
    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());//문자열 키 HMAC 암호화
        this.expiration = expiration;
    }

    //JWT 토큰 생성 메서드
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) //토큰에 username을 subject로 저장
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256) //비밀키로 HMAC서명
                .compact();//토큰 문자열 반환
    }

    //토큰 검증 및 username 추출
    public String validateAndExtractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {//유효하지 않은 토큰일 경우 null 반환
            return null;
        }
    }
} 