package model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import model.enums.UserRole;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;


public class Client extends User {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Client(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }

    public Client(UUID id, String name, Timestamp date) {
        this.setId(id.toString());
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }


    public void buyTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setUser(this);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public void printRole() {
        System.out.println("You are a Client");
    }

}
