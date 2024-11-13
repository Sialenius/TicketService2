package com.project.jfb.controller;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.model.request.TicketDetailsRequestModel;
import com.project.jfb.model.response.TicketRest;
import com.project.jfb.model.response.UserRest;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import com.project.jfb.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tickets") //http://localhost:8081/tickets
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<TicketRest> getAllTickets() {
        List<TicketRest> returnValue = new ArrayList<>();

        for (TicketDto t: ticketService.getAllTickets()) {
            TicketRest temptTicketRest = new TicketRest();
            BeanUtils.copyProperties(t, temptTicketRest);
            returnValue.add(temptTicketRest);
        }
        return returnValue;
    }

    @GetMapping("/{ticketId}")
    public TicketRest getTicketById(@PathVariable UUID ticketId) {
        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(ticketService.getTicket(ticketId), returnValue);

        return returnValue;
    }

    @GetMapping("/users/{userId}")
    public List<TicketEntity> getTicketsByUserId(@PathVariable UUID userId) {
        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping
    public TicketRest createTicket(@RequestBody TicketDetailsRequestModel ticketDetails) {

        TicketDto ticketDto = new TicketDto();
        BeanUtils.copyProperties(ticketDetails, ticketDto);
        System.out.println("USER ID: " + ticketDto.getUserId());

        TicketDto createTicket = ticketService.saveTicket(ticketDto);

        TicketRest returnValue = new TicketRest();
        BeanUtils.copyProperties(createTicket, returnValue);

        return returnValue;
    }

    @PutMapping("/{ticketId}/update-type")
    public void updateTicket(@PathVariable UUID ticketId, @RequestBody TicketDetailsRequestModel ticketDetailsRequestModel) {
        ticketService.updateTicketType(ticketId, ticketDetailsRequestModel.getTicketType());
    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
    }
}
