package example.spring.repository;

import example.spring.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountsRepository {

    private final SessionFactory sessionFactory;

    public AccountsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Account> getAccountById(long id) {
        Session session = sessionFactory.openSession();
        Account account = session.get(Account.class, id);
        session.close();
        return Optional.ofNullable(account);
    }

    public Long createAccount(Account account) {
        try (Session session = sessionFactory.openSession()) {
            return (Long) session.save(account);
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
