package example.spring.service;

import example.spring.exception.AccountNotFoundException;
import example.spring.model.Account;
import example.spring.model.enums.Gender;
import example.spring.repository.AccountsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private AccountsRepository accountsRepository;

    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public Account getAccountById(Long id) {
        return accountsRepository.getAccountById(id).orElseThrow(() -> new AccountNotFoundException("account with id:" + id + "not found"));
    }
    public List<Account> findExceedingBalance(List<Account> accounts, double balance) {
        return accounts.stream()
                .filter(account -> account.getBalance() > balance)
                .toList();
    }


    public Set<String> findUniqueCountry(List<Account> accounts) {
        if (accounts.size() == 0) {
            throw new IllegalArgumentException("Wrong size");
        }
        return accounts.stream()
                .map(Account::getCountry)
                .collect(Collectors.toSet());
    }

//    public boolean hasYoungerThan(List<Account> accounts, int date) {
//        return accounts.stream()
//                .anyMatch(account -> account.getYear() > date);
//    }

    public double findSumBalanceByGender(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> Gender.MALE.equals(account.getGender()))
                .mapToDouble(Account::getBalance)
                .sum();
    }

//    public Map<Integer, List<Account>> groupByMonth(List<Account> accounts) {
//        return accounts.stream()
//                .collect(Collectors.groupingBy(account -> account.getMonthValue()));
//    }
}
