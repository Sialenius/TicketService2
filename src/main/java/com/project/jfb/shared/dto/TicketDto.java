package com.project.jfb.shared.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TicketDto {

    private UUID ticketId;
    private UUID userId;
    private String ticketType = "DAY";
    private Timestamp creationDate;

}
