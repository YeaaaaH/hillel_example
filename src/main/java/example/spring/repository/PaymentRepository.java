package example.spring.repository;

import example.spring.model.Account;
import example.spring.model.Payment;
import example.spring.model.dto.PaymentDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {
    private SessionFactory sessionFactory;

    public PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    private void getPaymentById(Long id) {
//
//    }

//    public Long createPayment(PaymentDTO paymentdto) {
//        try(Session session = sessionFactory.openSession()){
//            Account account = session.get(Account.class, paymentdto.getAccountId());
//            Payment payment = Payment.builder()
//                    .amount(paymentdto.getAmount())
//                    .account(account)
//                    .build();
//            payment.setAccount(account);
//            return (Long)session.save(payment);
//        }
//    }

    public List<Payment> getAllPayments() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Payment> list = session.createCriteria(Payment.class).list();
            session.getTransaction().commit();
            return list;
        }
    }
}
