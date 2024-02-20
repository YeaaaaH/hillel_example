package spring.repository;

import spring.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

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
