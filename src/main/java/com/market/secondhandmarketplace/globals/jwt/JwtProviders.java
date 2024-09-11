package com.market.secondhandmarketplace.globals.jwt;


import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProviders implements InitializingBean {

    @Value("${jwt.headers}")
    public static String AUTHORITIES_KEY;
    private final String secretKey;
    private final long expirationTime;
    private final long refreshExpirationTime;

    private Key key;

    public JwtProviders(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.expiration}") long expirationTime,
            @Value("${jwt.refresh_expiration}") long refreshExpirationTime) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey.replace(" ",""));
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public MemberDto.LoginResponse createToken(String email, Long userId) {
        Claims claims = Jwts.claims();
        claims.put(AUTHORITIES_KEY, email);

        long now = new Date().getTime();
        Date accessExpiration = new Date(now + this.expirationTime);
        Date refreshExpiration = new Date(now + this.refreshExpirationTime);

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(accessExpiration)
                .setSubject(email)
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return MemberDto.LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException |
                 MalformedJwtException e) {
            log.info("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
            throw new JwtException("error");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if(claims.get("userId") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰 입니다.");
        }
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("userId").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails principal = new
        return new UsernamePasswordAuthenticationToken(claims.get("userId", Long.class), "", authorities);
    }
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
