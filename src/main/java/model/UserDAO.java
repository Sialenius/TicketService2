package model;

import model.enums.ConcertHall;
import model.enums.StadiumSector;
import model.enums.TicketType;
import model.enums.UserRole;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

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

    public ArrayList<User> getAllUsers()  {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate creationDate = Date.valueOf(rs.getString("creation_date")).toLocalDate();

                switch(rs.getString("user_role")) {
                    case "client" -> users.add(new Client(name, creationDate));
                    case "admin" -> users.add(new Admin(name, creationDate));
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void saveTicket(Ticket ticket, int userID) {
        String sql = "INSERT INTO tickets (user_id, ticket_type, creation_date) VALUES (?, ?::ticket_type, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, ticket.getTicketType());
            preparedStatement.setDate(3, Date.valueOf(ticket.getCreationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, creation_date, user_role) VALUES (?, ?, ?::user_type)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, Date.valueOf(user.getCreationDate()));
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    public ArrayList<Ticket> getAllTickets() {
        String sql = "SELECT * FROM tickets";
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int userID = (int) rs.getInt("user_id");
                LocalDate creationDate = Date.valueOf(rs.getString("creation_date")).toLocalDate();
                TicketType ticketType = null;
                switch (rs.getString("ticket_type")) {
                    case "empty":
                        ticketType = TicketType.EMPTY;
                        break;
                    case "limited":
                        ticketType = TicketType.LIMITED;
                        break;
                    case "full":
                        ticketType = TicketType.FULL;
                        break;
                }
                tickets.add(new Ticket(userID, ticketType, creationDate));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return tickets;
    }


    public Ticket getTicketByID(int ticketID) {
        String sql = "SELECT * FROM tickets WHERE id = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = (int) rs.getInt("user_id");
                LocalDate creationDate = Date.valueOf(rs.getString("creation_date")).toLocalDate();
                TicketType ticketType = null;
                switch(rs.getString("ticket_type")) {
                    case "empty":
                        ticketType = TicketType.EMPTY;
                        break;
                    case"limited":
                        ticketType = TicketType.LIMITED;
                        break;
                    case "full":
                        ticketType = TicketType.FULL;
                        break;
                }

                return new Ticket(userID,ticketType, creationDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void close() throws SQLException {
        rs.close();
        statement.close();
        connection.close();
    }
}
