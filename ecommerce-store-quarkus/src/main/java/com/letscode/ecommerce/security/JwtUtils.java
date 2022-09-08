package com.letscode.ecommerce.security;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import java.time.Instant;
import java.util.Set;

public class JwtUtils {

    private static final String ISSUER = "https://issuer.org";
    private static final long DURATION = 1800;
    private static final String SECRET = "4453fd5e8408dc24655669d0a37ef72e";

    public static String generateToken(String username, Set<String> roles) {
        JwtClaimsBuilder claimsBuilder = Jwt.claims()
                .issuer(ISSUER)
                .subject(username)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(DURATION))
                .groups(roles);
        return claimsBuilder.jws().signWithSecret(SECRET);
    }

}