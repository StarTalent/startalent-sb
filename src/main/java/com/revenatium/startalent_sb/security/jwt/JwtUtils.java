package com.revenatium.startalent_sb.security.jwt;

import com.revenatium.startalent_sb.config.StarTalentConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final StarTalentConfiguration starTalentConfiguration;

    public JwtUtils(StarTalentConfiguration starTalentConfiguration) {
        this.starTalentConfiguration = starTalentConfiguration;
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(starTalentConfiguration.getJwt().getExpirationMs())))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;

        } catch (Exception e) {
            log.error("Invalid token, error: ".concat(e.getMessage()));
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(starTalentConfiguration.getJwt().getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
