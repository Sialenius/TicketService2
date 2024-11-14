package com.project.jfb.controller;

import com.project.jfb.model.request.TicketTypeUpdateRequestModel;
import com.project.jfb.service.TicketService;
import com.project.jfb.shared.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets") //http://localhost:8081/tickets
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public TicketDto getTicketById(@PathVariable UUID ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @GetMapping("/users/{userId}")
    public List<TicketDto> getTicketsByUserId(@PathVariable UUID userId) {

        return ticketService.getTicketsByUserId(userId);
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {

        return ticketService.saveTicket(ticketDto);
    }

    @PutMapping("/{ticketId}/type")
    public void updateTicket(@PathVariable UUID ticketId, @RequestBody TicketTypeUpdateRequestModel ticketTypeUpdateRequestModel) {

        ticketService.updateTicketType(ticketId, ticketTypeUpdateRequestModel.getNewType());
    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable UUID ticketId) {

        ticketService.deleteTicket(ticketId);
    }
}
