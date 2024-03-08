//package test;
//
//import example.model.User;
//import example.utils.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.junit.jupiter.api.Test;
//
//class UserTest {
//
//    @Test
//    void initPostTest() {
//        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//             Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            User user = new User();
//            user.setUsername("Franklin");
//            session.save(user);
//            transaction.commit();
//        }
//    }
//}