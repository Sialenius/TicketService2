package com.project.jfb.service.impl;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.repository.TicketRepository;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketDto saveTicket(TicketDto ticketDto) {

        TicketEntity ticketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticketDto,ticketEntity);

        ticketEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        ticketEntity.setTicketType(TicketType.YEAR);

        TicketDto returnValue = new TicketDto();
        BeanUtils.copyProperties(ticketEntity, returnValue);
        return returnValue;
    }
}
