package example.spring.controller;

import example.spring.model.Account;
import example.spring.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountRestController {

    private final AccountService accountService;


    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody Account account) {
        Long result = accountService.createAccount(account);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> createAccount(@PathVariable Long id) {
         accountService.deleteAccountById(id);
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }

}
