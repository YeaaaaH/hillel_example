package spring.controller;

import spring.model.Account;
import spring.model.Payment;
import spring.model.dto.AccountDTO;
import spring.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Account getAccountById(@PathVariable Long id) {
//        return accountService.getAccountById(id);
//    }
//
//    @RequestMapping(value = "/{id}/payments", method = RequestMethod.GET)
//    public ResponseEntity<List<Payment>> getPaymentsByAccountById(@PathVariable Long id) {
//        List<Payment> payments = accountService.getPaymentsByAccountId(id);
//        return new ResponseEntity<>(payments, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Long> createAccount(@RequestBody AccountDTO account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

//    @DeleteMapping(value = "{id}")
//    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
//        accountService.deleteAccountById(id);
//        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
//    }

}
