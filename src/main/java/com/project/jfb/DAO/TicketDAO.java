package DAO;

import model.Ticket;
import model.User;
import model.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import view.Printable;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketDAO implements DAO<Ticket> {
    private static final String SAVE_TICKET_SQL = "INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String FETCH_TICKET_BY_ID_SQL = "SELECT * FROM tickets WHERE id = ?";
    private static final String FETCH_TICKET_BY_ID_AND_USER_SQL = "SELECT * FROM tickets WHERE id = ? AND user_id = ? ";
    private static final String SELECT_ALL_TICKETS_SQL = "SELECT * FROM tickets";
    private static final String UPDATE_TICKET_TYPE_SQL =  "UPDATE tickets SET ticket_type = ?::ticket_type WHERE id = ?";
    private static final String DELETE_TICKET_BY_ID_SQL = "DELETE FROM tickets WHERE id = ?";

    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public TicketDAO(@Autowired  DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ticket> getAll() {
        return jdbcTemplate.query(SELECT_ALL_TICKETS_SQL, new TicketMapper());
    }

    @Override
    public void save(Ticket ticket) {
        jdbcTemplate.update(SAVE_TICKET_SQL, ticket.getId(), ticket.getUserID(), ticket.getTicketType().name(), ticket.getCreationDate());
    }

    @Override
    public Ticket fetchByID(UUID id) {
        return jdbcTemplate.query(FETCH_TICKET_BY_ID_SQL, new Object[]{id.toString()}, new TicketMapper())
                .stream().findFirst().orElseThrow();
    }

    public Ticket fetchByTicketAndUserID(UUID userID, UUID ticketID) {
        return jdbcTemplate.query(FETCH_TICKET_BY_ID_AND_USER_SQL, new Object[]{ticketID.toString(), userID.toString()}, new TicketMapper())
                .stream().findFirst().orElse(null);

    }


    public void update(UUID ticketID, TicketType newType) {
        jdbcTemplate.update(UPDATE_TICKET_TYPE_SQL, newType.name(), ticketID.toString());
    }

    @Override
    public void delete(UUID uuid) {

        }
}


