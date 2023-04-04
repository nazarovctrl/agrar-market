package com.example.zoomarket.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.zoomarket.dto.JwtDTO;
import com.example.zoomarket.enums.ProfileRole;
import com.example.zoomarket.exp.auth.JWTTokenExpiredException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    private static final String accessTokenSecretKey = "51655468576D597133743677397A24432646294A404E635266556A586E327234";
    private static final String refreshTokenSecretKey = "28482B4D6251655468576D5A7134743777217A24432646294A404E635266556A";

    private static final int ACCESS_TOKEN_LIVE = 1000 * 3600 * 24; // 1day
    private static final int REFRESH_TOKEN_LIVE = 1000 * 3600 * 24 * 20; // 20day

    private static HashMap<String, Object> getClaims(String phone, ProfileRole role) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("phone", phone);
        claims.put("role", role.name());
        return claims;
    }

    public static String encodeAccessToken(String phone, ProfileRole role) {
        return Jwts
                .builder()
                .setClaims(getClaims(phone, role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_LIVE))
                .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                .compact();
    }

    public static String encodeRefreshToken(String phone, ProfileRole role) {
        return Jwts
                .builder()
                .setClaims(getClaims(phone, role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_LIVE))
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
                .compact();
    }

    public static JwtDTO decodeAccessToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(accessTokenSecretKey)
                .parseClaimsJws(token)
                .getBody();

        String phone = (String) claims.get("phone");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(phone, profileRole);

    }

    public static JwtDTO decodeRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(refreshTokenSecretKey)
                .parseClaimsJws(token)
                .getBody();

        String phone = (String) claims.get("phone");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(phone, profileRole);

    }

    public static boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Date expiresAt = decodedJWT.getExpiresAt();

        if (expiresAt.before(new Date())) {
            throw new JWTTokenExpiredException("JWT expired at " + expiresAt + ". Current time: " + new Date());
        }
        return false;

    }
}
