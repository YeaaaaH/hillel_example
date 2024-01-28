package service;

import main.java.model.Account;
import main.java.model.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest {
    static AccountService accountService;


    @BeforeAll
    static void setup() {
        accountService = new AccountService();
    }

    @Test
    public void findExceedingBalanceTest() {
        // given
        List<Account> accountList = new ArrayList<>();
        accountList.add(
                new Account(
                        "Antony",
                        "Hopkins",
                        "Usa",
                        LocalDate.of(1900, 01, 01),
                        Gender.MALE,
                        8000.0));
        accountList.add(new Account(
                "First",
                "Name",
                "Germany",
                LocalDate.of(1844, 12,12),
                Gender.FEMALE,
                6000.0));
        double exeedingValue = 7000.0;
        // when
        List<Account> result = accountService.findExceedingBalance(accountList, exeedingValue);
        // then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(8000.0, result.get(0).getBalance());
    }
    @Test
    public void expectedExceptionTest() {
        // given
        List<Account> accountList = new ArrayList<>();
        accountList.add(
                new Account(
                        "Antony","Hopkins", "Usa", LocalDate.of(1900, 01, 01),
                        Gender.MALE,
                        6999.2));
        accountList.add(new Account(
                "First","Name", "Germany", LocalDate.of(1844, 12,12),
                Gender.FEMALE,
                6000.0));
        double exeedingValue = 7000.0;
        // when
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    accountService.findUniqueCountry(new ArrayList<>());
                });
        assertEquals("Wrong size", exception.getMessage());
    }
}


