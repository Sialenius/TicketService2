package DAO;

import lombok.NonNull;
import model.Ticket;
import model.User;
import model.enums.TicketType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import view.Printable;

import java.util.List;


public class TicketDAO implements DAO<Ticket>, Printable  {
    public void save(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }

    public List<Ticket> fetchByTicketAndUserID(String ticketID, User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Ticket t WHERE t.id = :ticket_id AND t.user = :user_id";
        List ticket = session.createQuery(hql, Ticket.class)
                .setParameter("ticket_id", ticketID)
                .setParameter("user_id", user)
                .getResultList();

        transaction.commit();
        session.close();

        return ticket;
    }

    public void updateType(String ticketID, TicketType newType) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Ticket t SET t.ticketType = :newType WHERE t.id = :ticketID";
        if (session.createQuery(hql)
                .setParameter("newType", newType)
                .setParameter("ticketID", ticketID)
                .executeUpdate() > 0) {
            System.out.println("Ticket with ID: '" + ticketID + "' was updated.");
        }
        else {
            System.out.println("Ticket with ID: '" + ticketID + "' was not found.");
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(@NonNull String ticketID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Ticket ticket = session.get(Ticket.class, ticketID);
        if (ticket != null) {
            session.delete(ticket);
            System.out.println("Ticket with ID: '" + ticketID + "' was deleted.");
        } else {
            System.out.println("Ticket with ID: '" + ticketID + "' was not found.");
        }

        transaction.commit();
        session.close();
    }

    @Override
    public void printInformation() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Ticket";
        List<Ticket> tickets = session.createQuery(hql, Ticket.class).getResultList();

        for (Ticket t: tickets) {
            System.out.println(t.getId() + " | " + t.getTicketType() + " | " + t.getUser().getId());
        }

        transaction.commit();
        session.close();
    }

}
