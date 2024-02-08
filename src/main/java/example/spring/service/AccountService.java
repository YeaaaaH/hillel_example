package example.spring.service;

import example.spring.exception.AccountNotFoundException;
import example.spring.model.Account;
import example.spring.repository.AccountsRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountsRepository accountsRepository;

    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Account getAccountById(Long id) {
        return accountsRepository.getAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("account with id:" + id + "not found"));
    }

    public Long createAccount(Account account) {
        return accountsRepository.createAccount(account);
    }

    public void deleteAccountById(Long id) {
        accountsRepository.deleteAccountById(id);
    }
}
