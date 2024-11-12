package com.project.jfb.model.response;

import com.project.jfb.io.entity.enums.TicketType;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TicketRest {
    private UUID ticketId;
    private UUID userId;
    private TicketType ticketType;
    private Timestamp creationDate;

}
