package com.fqts.api_gateway.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.io.Decoders;
@Component
public class JwtUtils {
    //    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm    .HS256); // Generate a secure key
    public static SecretKey SECRETE_KEY= Jwts.SIG.HS256.key().build();
    public static long EXPIRATION_TIME= 5*60*1000;

    public String generateToken(String userName){
        return Jwts.builder().
                setSubject(userName).
                signWith(getSecreteKey()).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .compact();
    }


    public String extractUserName(String token){
        return Jwts.parser().verifyWith(getSecreteKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isTokenExpired(String token){
        Date expirationTime =Jwts.parser().verifyWith(getSecreteKey()).build().parseSignedClaims(token).getPayload().getExpiration();
        Date currentDate = new Date(System.currentTimeMillis());
        if(currentDate.after(expirationTime)){
            return false;
        }
        return true;
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUserName(token);

        if (extractedUsername.equals(username) && !isTokenExpired(token)) {
            return true;
        }

        throw new UsernameNotFoundException("Invalid JWT Token or Username");
    }


    public SecretKey getSecreteKey(){
        return SECRETE_KEY;
    }
}