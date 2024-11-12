package com.project.jfb.io.entity;


import com.project.jfb.io.entity.enums.UserRole;

import java.sql.Timestamp;
import java.util.UUID;

public class ClientEntity extends UserEntity {
    private TicketEntity ticketEntity;

    public ClientEntity(String name, Timestamp date) {
        setName(name);
        setCreationDate(date);
        setRole(UserRole.CLIENT);
    }

    public void buyTicket(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
        ticketEntity.setUserID(this.getId());
    }

    public TicketEntity getTicket() {
        return ticketEntity;
    }

}
