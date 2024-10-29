package DAO;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.Printable;

import java.util.List;

public class UserDAO  implements DAO<User>, Printable {

    public void save(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User fetchUser(String userID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User u WHERE u.id = :id";
        User user = session.createQuery(hql, User.class)
                .setParameter("id", userID)
                .getSingleResult();

        transaction.commit();
        return user;

    }

    @Transactional
    public void delete(@NonNull User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User u = session.get(User.class, user.getId());
        if (user != null) {
            session.remove(u);
            System.out.println("User with ID: '" + u.getId() + "' was deleted.");
        } else {
            System.out.println("User with ID: '" + u.getId() + "' was not found.");
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(@NonNull String id) {

    }

    @Transactional
    public void updateUserID(String userID, String newID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, userID);

        User u = session.get(User.class, user.getId());
        if (user != null) {
            session.evict(user);
            user.setId(newID);
            session.merge(user);
            System.out.println("User with ID: '" + u.getId() + "' was deleted.");
        } else {
            System.out.println("User with ID: '" + u.getId() + "' was not found.");
        }

        transaction.commit();
        session.close();

    }

    @Override
    public void printInformation() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM User";
        List<User> users = session.createQuery(hql, User.class).getResultList();

        for (User u: users) {
            System.out.println(u.getId() + " | " + u.getName() + " | " + u.getCreationDate());
        }

        transaction.commit();
        session.close();
    }
}
