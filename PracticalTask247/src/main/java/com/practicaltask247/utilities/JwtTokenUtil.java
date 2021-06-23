package com.practicaltask247.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public String getUsernameFromToken(String token, String secret) {
        return getClaimFromToken(token, secret, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token, String secret) {
        return getClaimFromToken(token, secret, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, String secret, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token, secret);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getExpirationDateFromToken(token, secret);
        return expiration.before(new Date());
    }

    public String generateToken(String username, UserDetails userDetails, String secret, long tokenValidity) {
        Map<String, Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        return doGenerateToken(claims, username, secret, tokenValidity);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String secret, long tokenValidity) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, String userName, String secret) {
        final String username = getUsernameFromToken(token, secret);
        return (username.equals(userName) && !isTokenExpired(token, secret));
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String token, String secret) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        List<SimpleGrantedAuthority> roles = null;

        Boolean isSeller = claims.get("isSeller", Boolean.class);
        Boolean isClient = claims.get("isClient", Boolean.class);

        if (isSeller != null && isSeller) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER"));
        }

        if (isClient != null && isClient) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
        return roles;

    }
}
