package ro.robert.epidemicrelief.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    public static final String SECRET_KEY = "792F423F4428472B4B6250655368566D597133743677397A244326462948404D";

    public String generateJwtToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user.getUsername());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date().getTime() + 86400000)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
//86400000 asta e cam 24 sau 12 h pot sa schimb in ceva mai mic
    public String extractUserMail(String token) {
        return (String) extractAllClaims(token).get("user");
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public boolean validateJwtToken(String token, UserDetails userDetails) {
        final String userEmail = extractUserMail(token);
        return Objects.equals(userEmail, userDetails.getUsername()) && verifyTokenNotExpired(token);
    }

    private boolean verifyTokenNotExpired(String token) {
        return extractExpiration(token).after(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
}