package example.spring.configuration;

import example.spring.model.Account;
import example.spring.model.enums.Gender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
//public class AccountsConfig {
//    @Bean
//    public List<Account> accounts() {
//        List<Account> accountList = new ArrayList<>();
//        accountList.add(
//                new Account(
//                        "Antony",
//                        "Hopkins",
//                        "Usa",
//                        Gender.MALE,
//                        8000.0));
//        accountList.add(new Account(
//                "First",
//                "Name",
//                "Germany",
//                Gender.FEMALE,
//                6000.0));
//        return accountList;
//    }
//}
