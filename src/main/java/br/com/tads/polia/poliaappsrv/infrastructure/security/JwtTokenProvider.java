package br.com.tads.polia.poliaappsrv.infrastructure.security;

import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;


    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        claims.put("user", user);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String generateTokenAdmin(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        claims.put("admin", admin);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }


    public boolean validateToken(String token) {
       try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token mal formado");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token nao suportado");
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (IllegalArgumentException e) {
            System.out.println("Token nulo");
        }
        return false;
    }

}