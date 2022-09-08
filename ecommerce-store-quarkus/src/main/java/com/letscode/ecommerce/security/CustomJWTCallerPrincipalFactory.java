package com.letscode.ecommerce.security;

import io.quarkus.arc.Priority;
import io.smallrye.jwt.auth.principal.*;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ApplicationScoped
@Alternative
@Priority(1)
public class CustomJWTCallerPrincipalFactory extends JWTCallerPrincipalFactory {

    @Override
    public JWTCallerPrincipal parse(String token, JWTAuthContextInfo authContextInfo) throws ParseException {
        try {
            String json = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);

            JwtClaims parse = JwtClaims.parse(json);

            if (NumericDate.now().isOnOrAfter(parse.getExpirationTime())) {
                throw new ParseException("Invalid token");
            }

            return new DefaultJWTCallerPrincipal(parse);
        } catch (InvalidJwtException ex) {
            throw new ParseException(ex.getMessage());
        } catch (MalformedClaimException e) {
            throw new RuntimeException(e);
        }
    }
}