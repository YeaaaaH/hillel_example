package spring.service;

import spring.exception.AccountNotFoundException;
import spring.model.Account;
import spring.model.Payment;
import spring.repository.AccountsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Payment> getPaymentsByAccountId(Long id) {
        return accountsRepository.getPaymentsByAccountId(id)
                .orElseThrow(() -> new AccountNotFoundException("account with id:" + id + "not found"));
    }

    public void createAccount(Account account) {
        accountsRepository.createAccount(account);
    }

    public void deleteAccountById(Long id) {
        accountsRepository.deleteAccountById(id);
    }
}
