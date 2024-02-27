package spring.service;

import spring.exception.AccountNotFoundException;
import spring.model.Account;
import spring.model.User;
import spring.model.dto.AccountDTO;
import spring.repository.AccountsRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountsRepository accountsRepository;
    private UserService userService;

    public AccountService(AccountsRepository accountsRepository, UserService userService) {
        this.accountsRepository = accountsRepository;
        this.userService = userService;
    }

    public Account getAccountById(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("account with id:" + id + "not found"));
    }

//TODO check how to use entity graph here

//    public List<Payment> getPaymentsByAccountId(Long id) {
//        return accountsRepository.getPaymentsByAccountId(id)
//                .orElseThrow(() -> new AccountNotFoundException("account with id:" + id + "not found"));
//    }

    public Long createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setBalance(accountDTO.getBalance());
        account.setCountry(accountDTO.getCountry());
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setGender(accountDTO.getGender());
        User user = userService.findUserById(accountDTO.getUserId());
        account.setUser(user);
        return accountsRepository.save(account).getId();
    }

    public void deleteAccountById(Long id) {
        accountsRepository.deleteById(id);
    }
}
