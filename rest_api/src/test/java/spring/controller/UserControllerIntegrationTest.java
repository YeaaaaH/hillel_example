package spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spring.model.User;
import spring.model.auth.SignInRequestDTO;
import spring.security.jwt.TokenProvider;
import spring.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Autowired
    TokenProvider tokenProvider;

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

    @Test
    void getAllUsersTest() {
        SignInRequestDTO signInRequestDTO = new SignInRequestDTO("admin", "password");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                signInRequestDTO.getUsername(), signInRequestDTO.getPassword(), List.of(new SimpleGrantedAuthority("CLIENT"))
        );

        String token = tokenProvider.createToken(usernamePasswordAuthenticationToken);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange("/api/user",
                        HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<>() {
                        });

        List<User> users = rateResponse.getBody();

        assertNotNull(users);
        assertEquals(1, users.size());
    }
}
