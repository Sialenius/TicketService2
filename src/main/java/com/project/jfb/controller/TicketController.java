package com.project.jfb.controller;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.model.request.TicketDetailsRequestModel;
import com.project.jfb.model.response.TicketRest;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tickets") //http://localhost:8081/tickets
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public String getAllTickets() {
        String returnValue = "";

        for (TicketEntity ticketEntity: ticketService.getAllTickets()) {
            returnValue += ticketEntity;
            returnValue += '\n';
        }

        return returnValue;
    }


    @GetMapping("/{id}")
    public TicketEntity getTicketById(@PathVariable UUID ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @GetMapping("/users/{userId}")
    public List<TicketEntity> getTicketsByUserId(@PathVariable UUID userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping
    public TicketRest createTicket(@RequestBody TicketDetailsRequestModel ticketDetails) {

        TicketDto ticketDto = new TicketDto();
        BeanUtils.copyProperties(ticketDetails, ticketDto);

        TicketDto createTicket = ticketService.saveTicket(ticketDto);

        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(createTicket, returnValue);

        return returnValue;
    }

    @PutMapping("/{id}/update-type")
    public void updateTicket(@PathVariable UUID ticketId, @RequestBody TicketType newticketType) {
        ticketService.updateTicketType(ticketId, newticketType);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
