package com.project.jfb.repository;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.enums.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {

    List<TicketEntity> findByUserId(UUID userId);

    @Modifying
    @Query("UPDATE TicketEntity t SET t.ticketType = :ticketType WHERE t.id = :id")
    void updateTicketTypeById(@Param("ticketType") TicketType ticketType, @Param("id") UUID id);
}
