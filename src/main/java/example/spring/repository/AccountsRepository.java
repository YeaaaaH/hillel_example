package example.spring.repository;

import example.spring.model.Account;
import example.spring.model.enums.Gender;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountsRepository {

    private List<Account> accountList;

    public AccountsRepository() {
        this.accountList = Collections.emptyList();
    }


    public Optional<Account> getAccountById(long id) {
        return accountList.stream()
                .filter(account -> account.getId().equals(id))
                .findAny();
    }
}
