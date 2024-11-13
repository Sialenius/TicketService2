package com.project.jfb.model.request;

import com.project.jfb.io.entity.enums.TicketType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketDetailsRequestModel {
    //private UUID ticketId;
    //private UUID userId;
    private TicketType ticketType;
    private Timestamp creationDate;

}