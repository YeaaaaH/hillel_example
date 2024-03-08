package spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.model.Payment;
import spring.model.User;
import spring.model.enums.Gender;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String firstName;
    private String lastName;
    private String country;
    private Gender gender;
    private Double balance;
    private Long userId;
}
