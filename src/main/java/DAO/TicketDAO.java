package DAO;

import model.Ticket;
import model.enums.TicketType;
import view.Printable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class TicketDAO implements DAO<Ticket>, Printable {
    private final Connection connection;

    private ArrayList<Ticket> tickets = new ArrayList<>();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Ticket ticket) {
        String sql = "INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticket.getID().toString());
            preparedStatement.setString(2, ticket.getUserID().toString());
            preparedStatement.setString(3, ticket.getTicketType().name());
            preparedStatement.setDate(4, Date.valueOf(ticket.getCreationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tickets.add(ticket);

    }

    @Override
    public Optional<Ticket> fetchByID(UUID id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnTicketID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType =  TicketType.valueOf(resultSet.getString("ticket_type"));
                LocalDate columnCreationDate = resultSet.getDate("creation_date").toLocalDate();

                return Optional.of(new Ticket(columnTicketID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public Optional<Ticket> fetchByTicketAndUserID(UUID ticketID, UUID userID) {
        String sql = "SELECT * FROM tickets WHERE id = ? AND user_id = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticketID.toString());
            preparedStatement.setString(2, userID.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnTicketID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType =  TicketType.valueOf(resultSet.getString("ticket_type"));
                LocalDate columnCreationDate = resultSet.getDate("creation_date").toLocalDate();

                return Optional.of(new Ticket(columnTicketID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }
        System.out.println("Invalid ticket or user UUID");
        return Optional.empty();
    }

    @Override
    public ArrayList<Ticket> getAll() {
        String  sql = "SELECT * FROM tickets";

        tickets.clear();

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UUID columnID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                LocalDate columnCreationDate = resultSet.getDate("creation_date").toLocalDate();

                tickets.add(new Ticket(columnID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tickets;
    }

    public void update(UUID id, TicketType newType) {
        String sql =  "UPDATE tickets SET ticket_type = ?::ticket_type WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newType.name());
            preparedStatement.setString(2, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(UUID uuid) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void printInformation() {
        tickets = this.getAll();
        System.out.println("=============================================== TABLE 'TICKETS' ===========================================\n" +
                "                 id                  |                user_id               |  ticket_type  | creation_date\n" +
                "-----------------------------------------------------------------------------------------------------------");
        if (tickets.isEmpty()) {
            System.out.println("empty\n");
        } else {
            for (Ticket t : tickets) {
                System.out.println(t.getID() + " | " + t.getUserID() + " |      " + t.getTicketType() + "     | " + t.getCreationDate());
            }
            System.out.print('\n');
        }
    }
}
