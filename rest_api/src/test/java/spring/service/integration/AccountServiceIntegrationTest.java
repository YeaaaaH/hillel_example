package spring.service.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;
import spring.security.jwt.TokenProvider;
import spring.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class AccountServiceIntegrationTest {

    @Autowired
    AuthService authService;

    @Autowired
    TokenProvider tokenProvider;

    SignInRequestDTO signInRequestDTO;

    @Container
    public static PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("any")
            .withUsername("user")
            .withPassword("pass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pgContainer::getJdbcUrl);
        registry.add("spring.datasource.username", pgContainer::getUsername);
        registry.add("spring.datasource.password", pgContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", pgContainer::getDriverClassName);
    }

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


