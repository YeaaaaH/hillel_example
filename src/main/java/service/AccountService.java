package service;

import main.java.model.Account;
import main.java.model.enums.Gender;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountService {
    public List<Account> findExceedingBalance(List<Account> accounts, double balance) {
        return accounts.stream()
                .filter(account -> account.balance() > balance)
                .toList();
    }


    public Set<String> findUniqueCountry(List<Account> accounts) {
        if (accounts.size() == 0) {
            throw new IllegalArgumentException("Wrong size");
        }
        return accounts.stream()
                .map(Account::country)
                .collect(Collectors.toSet());
    }

    public static boolean hasYoungerThan(List<Account> accounts, int date) {
        return accounts.stream()
                .anyMatch(account -> account.birthday().getYear() > date);
    }

    public static double findSumBalanceByGender(List<Account> accounts) {
        return accounts.stream()
                .filter(account -> Gender.MALE.equals(account.gender()))
                .mapToDouble(Account::balance)
                .sum();
    }

    public static Map<Integer, List<Account>> groupByMonth(List<Account> accounts) {
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> account.birthday().getMonthValue()));
    }
}
