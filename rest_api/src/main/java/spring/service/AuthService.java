package spring.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;
import spring.model.dto.UserDTO;
import spring.security.jwt.TokenProvider;

@Service
public class AuthService {

    private final AuthenticationProvider authenticationProvider;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public AuthService(AuthenticationProvider authenticationProvider, TokenProvider tokenProvider, UserService userService) {
        this.authenticationProvider = authenticationProvider;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    public SingInResponseDTO signin(SignInRequestDTO requestDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword());
        try {
            Authentication authenticate = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenProvider.createToken(authenticate);
            return new SingInResponseDTO(token);
        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public Long signup(UserDTO userDTO) {
        return userService.register(userDTO);
    }
}
