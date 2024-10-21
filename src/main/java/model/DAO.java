package model;

import model.enums.ConcertHall;
import model.enums.StadiumSector;

import java.sql.*;
import java.time.LocalDate;

import static model.AppConstants.UNSPECIFIED_DATE_TIME;


public class DAO {
    private final String URL = "jdbc:postgresql://localhost:5432/TicketServiceDB";
    private final String USER_NAME = "postgres";
    private final String PASSWORD = "123";

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs;

    public DAO() {
        try {
            // Class.forName("com.")
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public String getAllUsers()  {
        String sql = "SELECT * FROM users";
        String result = "TABLE 'USERS'\n" +
                "id | name | creation date\n" +
                "-------------------------\n";

        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                result += rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + '\n';
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public User fetchUserByID(int userID)  {
        User user = null;

        String sql = "SELECT * FROM users WHERE id = ?";
        String result = "SELECT user by ID = " + userID + '\n' +
                "id | name | creation date\n" +
                "------------------------------------------\n";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = switch (rs.getString(4)) {
                    case "client" -> new Client(rs.getString(2), Date.valueOf(rs.getString(3)).toLocalDate());
                    case "admin" -> new Admin(rs.getString(2), Date.valueOf(rs.getString(3)).toLocalDate());
                    default -> user;
                };
                result += rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + '\n';
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, creation_date) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, Date.valueOf(user.getCreationDate()));
            preparedStatement.setString(2, user.getRole());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUserByID(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTicket(Ticket ticket, int userID) {
        String sql = "INSERT INTO tickets (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, String.valueOf(ticket.getEventCode()));
            preparedStatement.setDate(3, Date.valueOf(ticket.getCreationDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchTicket(int ticketID, int userID) {
        Ticket result;

        String sql = "SELECT * FROM tickets";// WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(ticketID));
           // preparedStatement.setInt(2, userID);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(2));// + " | " + rs.getString(2) + " | " + rs.getString(3));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAllTickets() {
        String sql = "SELECT * FROM tickets";
        String result = "TABLE 'TICKETS'\n" +
                "id | user_id | ticket_type | creation date\n" +
                "------------------------------------------\n";

        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                result += rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + '\n';
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Ticket getTicketByID(int ticketID) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        Ticket ticket = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                switch(rs.getString(3)) {
                    case "empty":
                        ticket = new Ticket();
                        break;
                    case "limited":
                        ticket = new Ticket(ConcertHall.NOT_SPECIFIED, 10, UNSPECIFIED_DATE_TIME);
                        break;
                    case "full":
                        ticket = new Ticket(ConcertHall.NOT_SPECIFIED, 10, UNSPECIFIED_DATE_TIME, true, StadiumSector.B, 10, 10);
                        break;
                    default:
                        System.out.println("Incorrect ticket type");
                        System.exit(0);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }

    private void close() throws SQLException {
        rs.close();
        statement.close();
        connection.close();
    }
}
