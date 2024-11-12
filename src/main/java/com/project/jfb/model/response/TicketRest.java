package com.project.jfb.model.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TicketRest {
    private UUID ticketId;
    private UUID userId;
    private String ticketType;
    private Timestamp creationDate;

}
