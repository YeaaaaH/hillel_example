package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
