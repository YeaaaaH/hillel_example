package spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public class AuthControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    public static PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("any")
            .withUsername("user")
            .withPassword("pass");
    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + pgContainer.getJdbcUrl(),
                    "spring.datasource.username=" + pgContainer.getUsername(),
                    "spring.datasource.password=" + pgContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    void authServiceSignInTest() throws JsonProcessingException {
        SignInRequestDTO signInRequestDTO = new SignInRequestDTO("admin", "password");

        ResponseEntity<SingInResponseDTO> response = restTemplate.exchange(
                "/auth/signin",
                HttpMethod.POST,
                new HttpEntity<>(signInRequestDTO),
                SingInResponseDTO.class);
        response.getHeaders();
        SingInResponseDTO singInResponseDTO = response.getBody();

        assertNotNull(singInResponseDTO.getAccessToken());
    }


}
