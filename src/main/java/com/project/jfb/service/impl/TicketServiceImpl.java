package com.project.jfb.service.impl;

import com.project.jfb.repository.TicketRepository;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        return null;
    }
}
