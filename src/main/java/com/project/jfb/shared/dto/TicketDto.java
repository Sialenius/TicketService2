package com.project.jfb.shared.dto;

import com.project.jfb.io.entity.enums.TicketType;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TicketDto {

    private UUID ticketId;
    private UUID userId;
    private TicketType ticketType;
    private Timestamp creationDate;

}
