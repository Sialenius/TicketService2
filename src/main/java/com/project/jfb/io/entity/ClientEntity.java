package com.project.jfb.io.entity;


import java.sql.Timestamp;
import java.util.UUID;

public class ClientEntity extends UserEntity {
    private TicketEntity ticketEntity;

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


    public void buyTicket(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
        ticketEntity.setUserID(this.getUserId());
    }

    public TicketEntity getTicket() {
        return ticketEntity;
    }

}
