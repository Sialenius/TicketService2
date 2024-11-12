package com.project.jfb.io.entity;


import com.project.jfb.io.entity.enums.UserRole;

import java.sql.Timestamp;
import java.util.UUID;

public class ClientEntity extends UserEntity {
    private Ticket ticket;

    public ClientEntity(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setUserRole("Client");
    }

    public ClientEntity(UUID id, String name, Timestamp date) {
        this.setUserId(id);
        setName(name);
        setCreationDate(date);
        setUserRole("Client");
    }


    public void buyTicket(Ticket ticket) {
        this.ticket = ticket;
        ticket.setUserID(this.getUserId());
    }

    public Ticket getTicket() {
        return ticket;
    }

}
