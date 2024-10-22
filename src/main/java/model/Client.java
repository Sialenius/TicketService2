package model;


import model.enums.UserRole;

import java.time.LocalDate;
import java.util.UUID;

public class Client extends User {
    private Ticket ticket;

    public Client(String name, LocalDate date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }

    public Client(UUID id, String name, LocalDate date) {
        this.setID(id);
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }


    public void buyTicket(Ticket ticket) {
        this.ticket = ticket;
        ticket.setUserID(this.getID());
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public void printRole() {
        System.out.println("You are a Client");
    }

}
