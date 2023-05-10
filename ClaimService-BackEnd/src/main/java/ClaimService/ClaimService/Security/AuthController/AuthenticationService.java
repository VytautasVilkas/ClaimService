package ClaimService.ClaimService.Security.AuthController;

import ClaimService.ClaimService.Security.Enum.Role;
import ClaimService.ClaimService.Security.Model.User;
import ClaimService.ClaimService.Security.Repositories.UserRepository;
import ClaimService.ClaimService.Security.Config.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .Username(request.getUsername())
                .Password(passwordEncoder.encode(request.getPassword()))
                .Name(request.getName())
                .Surname(request.getSurname())
                .Email(request.getEmail())
                .role(Role.CLIENT)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse()
                .builder()
                .token(jwtToken)
                .build();

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.FindByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse()
                .builder()
                .token(jwtToken)
                .build();
    }
}
