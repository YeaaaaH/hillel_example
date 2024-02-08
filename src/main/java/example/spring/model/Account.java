package example.spring.model;

import example.spring.model.enums.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "accounts", schema = "management")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
//    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
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
