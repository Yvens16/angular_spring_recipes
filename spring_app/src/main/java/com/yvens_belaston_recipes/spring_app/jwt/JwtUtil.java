package com.yvens_belaston_recipes.spring_app.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.yvens_belaston_recipes.spring_app.dto.UserDto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtil {

  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode("SECRETKEYquiEstSuperLongSaGrandJeTeLeDisMoiMonAmi");
    // La clé doit faire 256bits
    Key key = Keys.hmacShaKeyFor(keyBytes);
    return key;
  }

  private String createToken(Map<String, Object> claims, String subject) {
    System.out.println("@@@@@@@@@@@ " + claims +  "Subject " +  subject);
    return Jwts
        .builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() +
            1000 * 60 * 60 * 10))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateToken(UserDto userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", userDetails.getRole());
    return createToken(claims, userDetails.getUsername());
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    System.out.println("&&&&&&&& +  "+ claims);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Boolean validateToken(String token,
      UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) &&
        !isTokenExpired(token));
  }
  
}


