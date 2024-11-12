package com.project.jfb.io.entity;

import com.project.jfb.io.entity.enums.UserRole;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class AdminEntity extends UserEntity {

    public AdminEntity(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.ADMIN);
    }

    public void checkTicket(TicketEntity ticketEntity) {
       if (Timestamp.valueOf(LocalDateTime.now()).after(ticketEntity.getEventTime())) {
           System.out.println("This ticket has expired");
       } else {
           System.out.println("This ticket is valid");
       }
    }
}
