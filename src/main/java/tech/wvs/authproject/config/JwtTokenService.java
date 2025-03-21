package tech.wvs.authproject.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.wvs.authproject.config.dto.JWTUserData;
import tech.wvs.authproject.entity.User;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtTokenService {

    //buscar valor no application.yml
    @Value("${authproject.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        //Definir um Algorithm
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("auth-project-api")
                .sign(algorithm);
    }

    public Optional<JWTUserData> validadeToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);


            return Optional.of(new JWTUserData(
                    (jwt.getClaim("userId").asLong()),
                    jwt.getClaim("name").asString(),
                    jwt.getSubject()
            ));


        } catch (
                JWTVerificationException e) {
            return Optional.empty();
        }

    }
}
