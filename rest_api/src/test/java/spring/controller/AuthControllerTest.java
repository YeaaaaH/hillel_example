package spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.exception.EntityExceptionHandler;
import spring.model.auth.SignInRequestDTO;
import spring.model.auth.SingInResponseDTO;
import spring.security.jwt.TokenProvider;
import spring.service.AuthService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthControllerTest.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @MockBean
    AuthService authService;

    @MockBean
    TokenProvider tokenProvider;

    MockMvc mockMvc;

    ObjectMapper mapper;

    SignInRequestDTO signInRequestDTO;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper();
        signInRequestDTO = new SignInRequestDTO("user", "password");
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AuthController(authService))
                .setControllerAdvice(new EntityExceptionHandler()).build();
    }

    @Test
    void singinTest() throws Exception {
        String jsonBody = mapper.writeValueAsString(signInRequestDTO);
        when(authService.signin(signInRequestDTO)).thenReturn(new SingInResponseDTO("token_test"));
        mockMvc.perform(post("/auth/signin").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(
                        "{\"accessToken\":\"token_test\"}", result.getResponse().getContentAsString()
                ))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void singinExceptionTest() throws Exception {
        String jsonBody = mapper.writeValueAsString(signInRequestDTO);
        when(authService.signin(signInRequestDTO)).thenThrow(new BadCredentialsException("Invalid username or password"));
        mockMvc.perform(post("/auth/signin").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof BadCredentialsException);
                })
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
