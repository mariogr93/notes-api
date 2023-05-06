package com.notes.notesdemo.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.notes.notesdemo.config.security.JwtConstants.JWT_EXPIRATION;
import static com.notes.notesdemo.config.security.JwtConstants.JWT_SECRET;

@Service
public class JwtService {

    public String generateToken(UserDetailsImpl userDetails){
        Date currentDate = new Date();
        Date expiredDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        String newToken = Jwts.builder()
                .setIssuer(userDetails.getUsername())
                .setSubject(String.format("%s,%s", userDetails.getId(), userDetails.getUsername()))
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
        return newToken;
    }

    public Boolean validateToken(String token, UserDetailsImpl userDetails) {
        String tokenUsername = getTokenUsername(token);
        try {
            return tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT WAS EXPIRED OR INCORRECT");
        }
    }

    public String getTokenUsername(String token){
        String userEmail = getJwtClaims(token).getSubject().split(",")[1];
        return userEmail;
    }

    private Date getTokenExpiration(String token){
        Date expDate = getJwtClaims(token).getExpiration();
        return expDate;
    }

    private boolean isTokenExpired(String token) {
        Date tokenExpiration = getTokenExpiration(token);
        return tokenExpiration.before(new Date());
    }

    private Claims getJwtClaims(String token) throws AuthenticationCredentialsNotFoundException {
        try {
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT WAS EXPIRED OR INCORRECT");
        }
    }
}
