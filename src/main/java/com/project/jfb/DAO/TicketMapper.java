package com.project.jfb.DAO;

import com.project.jfb.model.Ticket;
import com.project.jfb.model.enums.TicketType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setId(UUID.fromString(rs.getString("id")));
        ticket.setUserID(UUID.fromString(rs.getString("user_id")));
        ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
        ticket.setCreationDate(rs.getTimestamp("creation_date"));

        return ticket;
    }
}
