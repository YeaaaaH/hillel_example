package spring.service.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;
import spring.security.jwt.TokenProvider;
import spring.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceIntegrationTest {

    @Autowired
    AuthService authService;

    @Autowired
    TokenProvider tokenProvider;

    SignInRequestDTO signInRequestDTO;

    @BeforeEach
    void setup() {
        signInRequestDTO = new SignInRequestDTO("user", "password");
    }

    @Test
    void signinTest() {
        signInRequestDTO.setUsername("admin");
        SingInResponseDTO signin = authService.signin(signInRequestDTO);
        assertNotNull(signin.getAccessToken());
    }

    @Test
    void signinExceptionTest() {
        Exception exception = assertThrows(BadCredentialsException.class, () -> authService.signin(signInRequestDTO));
        assertEquals(exception.getClass(), BadCredentialsException.class);
        assertEquals("Invalid username or password", exception.getMessage());
    }
}


