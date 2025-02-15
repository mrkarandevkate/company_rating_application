package com.fqts.api_gateway.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final long EXPIRATION_TIME = 5 * 60 * 1000; // 5 Minutes
    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);

    public String generateToken(String userName, String role) {
        String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return Jwts.builder()
                .setSubject(userName)
                .claim("role", formattedRole)  // Ensure stored role has "ROLE_" prefix
                .signWith(SECRET_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }


    public String extractUserName(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String extractUserRole(String token) {
        String role = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
        logger.info("Extracted Role from Token: {}", role);
        return role;
    }


    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUserName(token);
            logger.info("Extracted Username from Token: {}", extractedUsername);
            logger.info("Expected Username: {}", username);

            return (extractedUsername.equals(username)) && !isTokenExpired(token);
        } catch (Exception e) {
            logger.error("JWT Validation failed: {}", e.getMessage());
            return false;
        }
    }


    public boolean isTokenExpired(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload().getExpiration().after(new Date());
    }
}
