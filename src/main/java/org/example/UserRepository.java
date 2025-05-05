import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addUser(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            session.save(user);
            session.getTransaction().commit();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}