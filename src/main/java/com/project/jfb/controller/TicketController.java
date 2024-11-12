package com.project.jfb.controller;

import com.project.jfb.model.request.TicketDetailsRequestModel;
import com.project.jfb.model.response.TicketRest;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tickets") //http://localhost:8081/tickets
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    public String getTicket() {
        return "get ticket was called from TicketService";
    }

    @PostMapping
    public TicketRest saveTicket(@RequestBody TicketDetailsRequestModel ticketDetails) {
        TicketRest returnValue = new TicketRest();

        TicketDto ticketDto = new TicketDto();
        BeanUtils.copyProperties(ticketDetails, ticketDto);

        TicketDto createTicket = ticketService.saveTicket(ticketDto);
        BeanUtils.copyProperties(ticketDto, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateTicket() {
        return "update ticket was called";
    }

    @DeleteMapping
    public String deleteTicket() {
        return "delete ticket was called";
    }
}
