package model;

import jakarta.persistence.Table;
import lombok.Data;
import model.enums.UserRole;

import java.lang.annotation.Annotation;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Entity;


public class Admin extends User {

    public Admin(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.ADMIN);
    }

    public Admin(UUID id, String name, Timestamp date) {
        this.setId(id.toString());
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

}