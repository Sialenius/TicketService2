package model;


import model.enums.UserRole;

import java.time.LocalDate;

public class Client extends User {
    private Ticket ticket;

    public Client(String name, LocalDate date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }

    public void buyTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Client: " + '\n' +
                "ID: " + this.getId() + '\n' +
                "Name: " + this.getName() + '\n' +
                "Creation date: " + this.getCreationDate() + '\n';// +
                //ticket.toString();
    }

    @Override
    public void printRole() {
        System.out.println("You are a Client");
    }

}
