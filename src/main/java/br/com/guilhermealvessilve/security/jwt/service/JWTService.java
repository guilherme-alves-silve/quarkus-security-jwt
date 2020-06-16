package br.com.guilhermealvessilve.security.jwt.service;

import br.com.guilhermealvessilve.security.jwt.data.Token;
import br.com.guilhermealvessilve.security.jwt.utils.TokenUtils;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class JWTService {

    public Token generateToken(
            final String email,
            final String username,
            final String nickname,
            final String role
    ) throws Exception {
        final var jwtClaims = new JwtClaims();
        jwtClaims.setIssuer("Guilherme");
        jwtClaims.setJwtId("a-123");
        jwtClaims.setSubject(email);
        jwtClaims.setClaim(Claims.email.name(), email);
        jwtClaims.setClaim(Claims.preferred_username.name(), username);
        jwtClaims.setClaim(Claims.nickname.name(), nickname);
        jwtClaims.setClaim(Claims.groups.name(), List.of(role));
        jwtClaims.setAudience("using-jwt");
        jwtClaims.setExpirationTimeMinutesInTheFuture(1);
        final var value = TokenUtils.generateTokenString(jwtClaims);
        return new Token(value);
    }
}
