package com.project.jfb.service;

import com.project.jfb.shared.dto.TicketDto;

import java.util.UUID;

public interface TicketService {

    TicketDto saveTicket(TicketDto ticketDto);

    TicketDto getTicketById(UUID id);
}
