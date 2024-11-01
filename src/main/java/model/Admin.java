package model;

import model.enums.UserRole;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Admin extends User {

    public Admin(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.ADMIN);
    }

    public Admin(UUID id, String name, Timestamp date) {
        this.setId(id);
        setName(name);
        setCreationDate(date);
        setRole(UserRole.ADMIN);
    }

    public void checkTicket(Ticket ticket) {
       if (Timestamp.valueOf(LocalDateTime.now()).after(ticket.getEventTime())) {
           System.out.println("This ticket has expired");
       } else {
           System.out.println("This ticket is valid");
       }
    }

    @Override
    public void printRole() {
        System.out.println("You are an Admin");
    }
}
