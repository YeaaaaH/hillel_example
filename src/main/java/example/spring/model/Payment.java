package example.spring.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "account")
//@EqualsAndHashCode(exclude = "account")
@Entity
@Table
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

//    @ManyToOne
////    @JoinColumn(nullable = false)
//    private Account account;
}
