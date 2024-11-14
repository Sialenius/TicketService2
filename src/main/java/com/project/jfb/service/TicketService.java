package com.project.jfb.service;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.repository.TicketRepository;
import com.project.jfb.shared.dto.TicketDto;
import com.project.jfb.shared.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional
    public TicketDto getTicket(UUID id) {

        TicketEntity findedTicket = ticketRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ticket with id: " + id + " was not found."));

        TicketDto ticketDto = new TicketDto();

        BeanUtils.copyProperties(findedTicket, ticketDto);

        return ticketDto;
    }

    @Transactional
    public List<TicketDto> getTicketsByUserId(UUID userId) {
        List<TicketDto> allTickets = new ArrayList<>();

        for (TicketEntity t: ticketRepository.findByUserId(userId)) {
            TicketDto temptTicketDto = new TicketDto();
            BeanUtils.copyProperties(t, temptTicketDto);
            allTickets.add(temptTicketDto);
        }
        return allTickets;

    }

    @Transactional
    public List<TicketDto> getAllTickets() {
        List<TicketDto> allTickets = new ArrayList<>();

        for (TicketEntity t: ticketRepository.findAll()) {
            TicketDto temptTicketDto = new TicketDto();
            BeanUtils.copyProperties(t, temptTicketDto);
            allTickets.add(temptTicketDto);
        }
        return allTickets;
    }

    @Transactional
    public TicketDto saveTicket(TicketDto ticketDto) {

        TicketEntity ticketEntity = new TicketEntity();
        if (ticketDto.getTicketType() == null) {
            BeanUtils.copyProperties(ticketDto, ticketEntity, "ticketType");
        } else {
            BeanUtils.copyProperties(ticketDto,ticketEntity);
        }

        ticketEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));

        TicketEntity createdTicket = ticketRepository.save(ticketEntity);

        TicketDto returnValue = new TicketDto();
        BeanUtils.copyProperties(createdTicket, returnValue);

        return returnValue;
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
