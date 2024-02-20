package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.configuration.AppConfigTest;
import spring.controller.AccountRestController;
import spring.exception.AccountNotFoundException;
import spring.exception.EntityExceptionHandler;
import spring.service.AccountService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfigTest.class)
class AccountControllerTest {

    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AccountRestController(accountService)).setControllerAdvice(new EntityExceptionHandler()).build();
    }

    @Test
    void loadContext() throws Exception {
        MvcResult result1 = mockMvc.perform(get("/api/account/1"))
                .andExpect(result -> {
                    System.out.println(result.getResolvedException().toString());
                    assertTrue(result.getResolvedException() instanceof AccountNotFoundException);
                })
                .andExpect(result -> assertEquals("account with id:1not found", result.getResolvedException().getMessage()))
                .andReturn();


        Optional<AccountNotFoundException> someException = Optional.ofNullable((AccountNotFoundException) result1.getResolvedException());
    }
}
