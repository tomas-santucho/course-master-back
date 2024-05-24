package codingdojo.cl.coursemaster.security.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtService {
    private static final String SECRET = "your-secret";
    private static final String ISSUER = "your-issuer";

    public String createToken(String username, int minutesValid) {
        var algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (long) minutesValid * 60 * 1000))
                .sign(algorithm);
    }


    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        var algorithm = Algorithm.HMAC256(SECRET);
        var verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public String extractUserId(String token) {
        var jwt = JWT.decode(token);
        return jwt.getClaim("userId").asString();
    }
}
