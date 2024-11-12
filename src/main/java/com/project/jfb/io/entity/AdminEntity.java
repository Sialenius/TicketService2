package com.project.jfb.io.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class AdminEntity extends UserEntity {

    public AdminEntity(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setUserRole("Admin");
    }

    public AdminEntity(UUID id, String name, Timestamp date) {
        this.setUserId(id);
        setName(name);
        setCreationDate(date);
        setUserRole("Admin");
    }

    public void checkTicket(TicketEntity ticketEntity) {
       if (Timestamp.valueOf(LocalDateTime.now()).after(ticketEntity.getEventTime())) {
           System.out.println("This ticket has expired");
       } else {
           System.out.println("This ticket is valid");
       }
    }
}
