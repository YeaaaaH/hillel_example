package example.spring.model;

import example.spring.model.enums.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
@Data
@RequiredArgsConstructor
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
//    private LocalDate birthday;
    private Gender gender;
    private Double balance;

    public Account(Long id, String firstName, String lastName, String country, Gender gender, Double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.gender = gender;
        this.balance = balance;
    }
}
