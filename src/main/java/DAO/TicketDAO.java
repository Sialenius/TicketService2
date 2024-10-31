package DAO;

import config.ConfigurationObject;
import config.ConnectionConfiguration;
import model.Ticket;
import model.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import view.Printable;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketDAO implements DAO<Ticket>, Printable {
    private static final String SAVE_TICKET_SQL = "INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String FETCH_TICKET_BY_ID_SQL = "SELECT * FROM tickets WHERE id = ?";
    private static final String FETCH_TICKET_BY_ID_AND_USER_SQL = "SELECT * FROM tickets WHERE id = ? AND user_id = ? ";
    private static final String SELECT_ALL_TICKETS_SQL = "SELECT * FROM tickets";
    private static final String UPDATE_TICKET_TYPE_SQL =  "UPDATE tickets SET ticket_type = ?::ticket_type WHERE id = ?";
    private static final String DELETE_TICKET_BY_ID_SQL = "DELETE FROM tickets WHERE id = ?";

    private Connection connection;

    private ArrayList<Ticket> tickets = new ArrayList<>();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    ConfigurationObject configuration;

    private DataSource dataSource;



    public TicketDAO(ConfigurationObject configuration) {
        this.configuration = configuration;
    }

    public TicketDAO(@Autowired  DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Ticket ticket) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_TICKET_SQL);
            preparedStatement.setString(1, ticket.getID().toString());
            preparedStatement.setString(2, ticket.getUserID().toString());
            preparedStatement.setString(3, ticket.getTicketType().name());
            preparedStatement.setTimestamp(4, ticket.getCreationDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        tickets.add(ticket);
    }

    @Override
    public Optional<Ticket> fetchByID(UUID id) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FETCH_TICKET_BY_ID_SQL);
            preparedStatement.setString(1, id.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnTicketID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType =  TicketType.valueOf(resultSet.getString("ticket_type"));
                Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");

                return Optional.of(new Ticket(columnTicketID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    public Optional<Ticket> fetchByTicketAndUserID(UUID ticketID, UUID userID) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FETCH_TICKET_BY_ID_AND_USER_SQL);
            preparedStatement.setString(1, ticketID.toString());
            preparedStatement.setString(2, userID.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnTicketID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType =  TicketType.valueOf(resultSet.getString("ticket_type"));
                Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");

                return Optional.of(new Ticket(columnTicketID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Invalid ticket or user UUID");
        return Optional.empty();
    }

    @Override
    public ArrayList<Ticket> getAll() {
        tickets.clear();

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS_SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UUID columnID = UUID.fromString(resultSet.getString("id"));
                UUID columnUserID = UUID.fromString(resultSet.getString("user_id"));
                TicketType columnTicketType = TicketType.valueOf(resultSet.getString("ticket_type"));
                Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");

                tickets.add(new Ticket(columnID, columnUserID, columnTicketType, columnCreationDate));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return tickets;
    }

    public void update(UUID id, TicketType newType) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TICKET_TYPE_SQL);
            preparedStatement.setString(1, newType.name());
            preparedStatement.setString(2, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(UUID uuid) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_TICKET_BY_ID_SQL);
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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
