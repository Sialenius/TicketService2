package service;

import DAO.TicketDAO;
import model.*;

import model.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import view.Printable;

import java.util.List;
import java.util.UUID;


@Service
public class TicketService implements Printable {

    @Autowired
    private TicketDAO ticketDAO;

    @Value("${isUsersUpdateAndTicketsCreationAvailable}")
    private boolean isTicketsCreationAvailable;


    public List<Ticket> getAll() {
        return ticketDAO.getAll();
    }

    @Transactional
    public void register(Ticket ticket) {
        if (isTicketsCreationAvailable == true) {
            ticketDAO.save(ticket);
        } else {
            throw new IllegalAccessError("Creating new tickets is not availanle");
        }
    }



    public Ticket fetchByID(UUID ticketID) {
        Ticket ticket = ticketDAO.fetchByID(ticketID);
        if (ticket == null) {
            System.out.println("The ticket with ID: '" + ticketID + "' doesn't exist.");
            System.exit(0);
        }
        return ticket;
    }


    public Ticket fetchByUserAndTicketID(UUID userID, UUID ticketID) {
        Ticket ticket = ticketDAO.fetchByTicketAndUserID(userID, ticketID);
        if (ticket == null) {
            System.out.println("The ticket with ID: '" + ticketID + "' and userID: '" + userID + "' doesn't exist.");
            System.exit(0);
        }
        return ticket;
    }

    @Transactional
    public void updateTicketType(UUID ticketID, TicketType newTicketType) {
        fetchByID(ticketID);

        ticketDAO.update(ticketID, newTicketType);
    }

    @Override
    public void printInformation() {
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


}
