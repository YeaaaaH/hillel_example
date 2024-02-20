package spring.repository;

import spring.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<User> list = session.createCriteria(User.class).list();
            for (User user: list) {
                Hibernate.initialize(user.getAccounts());
            }
            session.getTransaction().commit();
            return list;
        }
    }
}
