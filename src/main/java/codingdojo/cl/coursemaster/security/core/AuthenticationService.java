package codingdojo.cl.coursemaster.security.core;

import codingdojo.cl.coursemaster.security.user.domain.CourseUser;
import codingdojo.cl.coursemaster.security.user.data.CourseUserRepository;
import codingdojo.cl.coursemaster.security.rest.dto.AuthResponse;
import codingdojo.cl.coursemaster.security.rest.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CourseUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(final String username, final String password) throws AuthenticationException {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var user = (CourseUser) authentication.getPrincipal();
        return new AuthResponse(user.getName(), jwtService.createToken(user.getUsername(), 60), user.getId());
    }

    public AuthResponse register(final RegisterRequest re) {
        if (userRepository.findByEmail(re.user()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        var newUser = userRepository.save(new CourseUser(re.name(), re.user(), passwordEncoder.encode(re.pass())));
        return new AuthResponse(newUser.getName(), jwtService.createToken(newUser.getUsername(), 60), newUser.getId());
    }
}