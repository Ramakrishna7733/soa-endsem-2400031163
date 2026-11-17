
package com.klef.soa.endsem.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtUtil
{
    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey12";

    private SecretKey getKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username)
    {
        long currentTime = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(currentTime))
                .expiration(new Date(currentTime + 3600000))
                .signWith(getKey())
                .compact();
    }

    public boolean validateToken(String token)
    {
        try
        {
            Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);

            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
