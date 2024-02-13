package example.spring.model;

import example.spring.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = "accountDetails")
//@EqualsAndHashCode(exclude = "accountDetails")
@Builder
@Table(name = "accounts", schema = "management")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
//    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Double balance;

//    @OneToOne(mappedBy = "account")
//    private AccountDetails accountDetails;
//    @OneToMany(mappedBy = "account")
//    private List<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private List<Payment> payments;
}