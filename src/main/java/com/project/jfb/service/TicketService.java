package com.project.jfb.service;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.repository.TicketRepository;
import com.project.jfb.shared.dto.TicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Transactional
    public TicketDto saveTicket(TicketDto ticketDto) {
        TicketEntity ticketEntity = new TicketEntity();
        BeanUtils.copyProperties(ticketDto,ticketEntity);

        ticketEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        ticketEntity.setTicketType(TicketType.YEAR);
        ticketEntity.setUserId(new UserEntity().getId());

        TicketEntity createdTicket = ticketRepository.save(ticketEntity);
        TicketDto returnValue = new TicketDto();
        BeanUtils.copyProperties(createdTicket, returnValue);

        return returnValue;
    }

    @Transactional
    public TicketEntity getTicket(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket with id: " + id + " was not found."));
    }

    @Transactional
    public List<TicketEntity> getTicketsByUserId(UUID userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Transactional
    public List<TicketEntity> getAllTickets() {
        return (List<TicketEntity>) ticketRepository.findAll();
    }

    @Transactional
    public void updateTicketType(UUID id, TicketType ticketType) {
        ticketRepository.updateTicketTypeById(ticketType, id);
    }

    @Transactional
    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }
}
