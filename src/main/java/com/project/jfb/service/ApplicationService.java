package com.project.jfb.service;

import com.project.jfb.DAO.TicketDAO;
import com.project.jfb.DAO.UserDAO;
import com.project.jfb.model.Ticket;
import com.project.jfb.model.User;
import com.project.jfb.model.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import com.project.jfb.view.Printable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class ApplicationService implements Printable {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TicketDAO ticketDAO;

    @Value("${isUsersUpdateAndTicketsCreationAvailable}")
    private boolean isUsersUpdateAndTicketsCreationAvailable;


    public List<User> getAllUsers() {
        return userDAO.getAll();
    }


    @Transactional
    public void registerUser(User user) {
        userDAO.save(user);
    }


    public User fetchByUserID(UUID userID) {
        User user = userDAO.fetchByID(userID);
        if (user == null) {
            System.out.println("The user with ID: '" + userID + "' doesn't exist.");
            System.exit(0);
        }
        return user;
    }


    @Transactional
    public void updateUserIDAndCreateTicket(UUID userID, UUID newUserID) throws IllegalAccessException {
       if (!isUsersUpdateAndTicketsCreationAvailable) {
           throw new IllegalAccessException("Updating users is no available");
       }
        fetchByUserID(userID);
        userDAO.updateUserID(userID, newUserID);
        ticketDAO.save(new Ticket(newUserID, TicketType.DAY, Timestamp.valueOf(LocalDateTime.now())));
    }

    @Transactional
    public void removeUser(UUID userID) {
        fetchByUserID(userID);

        userDAO.delete(userID);
    }

    @Transactional
    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

    public void printUsersInformation() {
        System.out.println("=========================== TABLE 'USERS_INFO' ==========================\n" +
                "                  id                 |  name  | creation_date | user_role  \n" +
                "--------------------------------------------------------------");
        if (userDAO.getAll().isEmpty()) {
            System.out.println("empty\n");
        } else {
            for (User u: userDAO.getAll()) {
                System.out.println(u.getId() + " | " + u.getName() + " | " + u.getCreationDate() + " | " + u.getRole());
            }
            System.out.print('\n');
        }
    }


    public List<Ticket> getAllTickets() {
        return ticketDAO.getAll();
    }

    @Transactional
    public void registerTicket(Ticket ticket) {
        if (!isUsersUpdateAndTicketsCreationAvailable) {
            throw new IllegalAccessError("Creating new tickets is not available");
        }
            ticketDAO.save(ticket);

    }


    public Ticket fetchTicketByID(UUID ticketID) {
        Ticket ticket = ticketDAO.fetchByID(ticketID);
        if (ticket == null) {
            System.out.println("The ticket with ID: '" + ticketID + "' doesn't exist.");
            System.exit(0);
        }
        return ticket;
    }

    public Ticket fetchTicketByUserAndTicketID(UUID userID, UUID ticketID) {
        Ticket ticket = ticketDAO.fetchByTicketAndUserID(userID, ticketID);
        if (ticket == null) {
            System.out.println("The ticket with ID: '" + ticketID + "' and userID: '" + userID + "' doesn't exist.");
            System.exit(0);
        }
        return ticket;
    }

    @Transactional
    public void updateTicketType(UUID ticketID, TicketType newTicketType) {
        fetchTicketByID(ticketID);

        ticketDAO.update(ticketID, newTicketType);
    }

    public void printTicketInformation() {
        System.out.println("=============================================== TABLE 'TICKETS' ===========================================\n" +
                "                 id                  |                user_id               |  ticket_type  | creation_date\n" +
                "-----------------------------------------------------------------------------------------------------------");
        if (ticketDAO.getAll().isEmpty()) {
            System.out.println("empty\n");
        } else {
            for (Ticket t : ticketDAO.getAll()) {
                System.out.println(t.getId() + " | " + t.getUserID() + " |      " + t.getTicketType() + "     | " + t.getCreationDate());
            }
            System.out.print('\n');
        }
    }

    @Override
    public void printInformation() {
        printUsersInformation();
        printTicketInformation();
    }
}
