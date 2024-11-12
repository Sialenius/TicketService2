package com.project.jfb.model.request;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TicketDetailsRequestModel {
    private UUID ticketId;
    private UUID userId;
    private String ticketType;
    private Timestamp creationDate;

}
