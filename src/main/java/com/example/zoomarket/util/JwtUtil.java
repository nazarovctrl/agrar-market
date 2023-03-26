package com.example.zoomarket.util;

import com.example.zoomarket.dto.JwtDTO;
import com.example.zoomarket.enums.ProfileRole;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    private static final String secretKey = "topsecretKey!123";
    private static final int tokenLiveTime = 1000 * 3600 * 24; // 1-day

    public static String encode(String phone, ProfileRole role) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey);

        jwtBuilder.claim("phone", phone);

        jwtBuilder.claim("role", role);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (tokenLiveTime)));
        jwtBuilder.setIssuer("MyHealth test portali");
        return jwtBuilder.compact();
    }

    public static JwtDTO decodeToken(String token) {

        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(secretKey);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String phone = (String) claims.get("phone");

        String role = (String) claims.get("role");
        ProfileRole profileRole = ProfileRole.valueOf(role);

        return new JwtDTO(phone, profileRole);

    }

}
