package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfigTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.controller.UserController;
import spring.exception.EntityExceptionHandler;
import spring.exception.UserNotFoundException;
import spring.model.User;
import spring.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AppConfigTest.class})
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class UserControllerTest {

    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setControllerAdvice(new EntityExceptionHandler()).build();
    }

    @Test
    void findNotExistingUser() throws Exception {
        mockMvc.perform(get("/api/user/999"))
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof UserNotFoundException);
                })
                .andExpect(result -> assertEquals("User not found", result.getResolvedException().getMessage()))
                .andReturn();
    }

    @Test
    void findExistingUser() throws Exception {
        User user = new User("Bobby");
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(user);
        mockMvc.perform(post("/api/user").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void saveAccount() throws Exception {
        User user = new User("Johny");
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(user);

        mockMvc.perform(post("/api/user")
                        .content(jsonBody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
