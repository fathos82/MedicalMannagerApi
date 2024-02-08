package med.vol.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.vol.api.domain.dtos.request.LoginRequest;
import med.vol.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private static String SECRET;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(user.getPassword())
                    .withExpiresAt(getDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token.");
        }
    }

    private Instant getDate() {
        return LocalDateTime
                .now().plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
