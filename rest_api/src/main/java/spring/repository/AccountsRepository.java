package spring.repository;

import spring.model.Account;
import spring.model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountsRepository {

    private final SessionFactory sessionFactory;

    public AccountsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Account> getAccountById(long id) {
        Account account;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            account = session.get(Account.class, id);
            session.getTransaction().commit();
        }
        return Optional.ofNullable(account);
    }

    public Optional<List<Payment>> getPaymentsByAccountId(long id) {
        Account account;
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, id);
            List<Payment> payments = account.getPayments();
            return Optional.ofNullable(payments);
        }
    }

    public Long createAccount(Account account) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Payment> payments = account.getPayments();
            for (Payment payment : payments) {
                session.save(payment);
            }
            Long id = (Long) session.save(account);
            session.getTransaction().commit();
            return id;
        }
    }

    public void deleteAccountById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Account account = session.load(Account.class, id);
            session.delete(account);
            session.flush();
        }
    }
}