package med.vol.api.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.vol.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.jwt.secret}")
    private static String SECRET;
    private static final String ISSUER = "API Voll" ;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getLogin())
                    .withExpiresAt(getDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token.");
        }
    }

    public  String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error verifying token.");
        }

    }
    private Instant getDate() {
        return LocalDateTime
                .now().plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
