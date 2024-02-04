package example.spring.controller;

import example.spring.model.Account;
import example.spring.service.AccountService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountRestController {
//    @Autowired
//    private List<Account> accounts;

    private final AccountService accountService;

//    private final List<Account> accounts;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
//        this.accounts = accounts;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Account createAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

//
//    @RequestMapping(value = "/balance/{balanceAmount}", method = RequestMethod.GET)
//    public List<Account> account(@PathVariable double balanceAmount) {
//        return accountService.findExceedingBalance(accounts, balanceAmount);
//    }
//
//    @RequestMapping(value = "/balance", method = RequestMethod.GET)
//    public List<Account> account(@RequestParam int balanceAmount) {
//        return accountService.findExceedingBalance(accounts, balanceAmount);
//    }
//
//    @RequestMapping(value = "/balance", method = RequestMethod.POST)
//    public double account(@RequestBody List<Account> inputAccounts) {
//        return accountService.findSumBalanceByGender(inputAccounts);
//    }

}
