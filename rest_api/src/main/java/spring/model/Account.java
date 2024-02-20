package spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.model.enums.Gender;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private List<Payment> payments;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}