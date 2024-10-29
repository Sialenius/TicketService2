package DAO;

import model.Ticket;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryProvider {
    private static SessionFactory factory;

    private SessionFactoryProvider() {}

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Ticket.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                factory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return factory;
    }
}
