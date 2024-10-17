package main.java.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Admin extends User {

    public void checkTicket(Ticket ticket) {
       if (Timestamp.valueOf(LocalDateTime.now()).after(ticket.getEventTime())) {
           System.out.println("This ticket has expired");
       } else {
           System.out.println("This ticket is valid");
       }
    }

    @Override
    public String toString() {
        return "Admin: " + "\n" +
                "ID: " + this.getId();
    }

    @Override
    public void printRole() {
        System.out.println("You are an Admin");
    }
}
