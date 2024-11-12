package com.project.jfb.repository;

import com.project.jfb.io.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findTicketByUserID(String name);

    Ticket findTicketByIdAndUserId(UUID id, UUID userId);
}