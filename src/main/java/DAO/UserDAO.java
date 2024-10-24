package DAO;

import config.ConnectionConfiguration;
import model.Admin;
import model.Client;
import model.User;
import model.enums.UserRole;
import view.Printable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


public class UserDAO implements DAO<User>, Printable {
    private Connection connection;
    
    private ArrayList<User> users = new ArrayList<>();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDAO() {
    }

    public void deleteAll() {
        String sql = "DELETE FROM users_info";

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(sql);
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
    public void save(User user) {
        String sql = "INSERT INTO users_info (id, name, creation_date, user_role) VALUES (?, ?, ?, ?::user_role)";
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getID().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setDate(3, Date.valueOf(user.getCreationDate()));
            preparedStatement.setString(4, user.getRole().name());
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
    public Optional<User> fetchByID(UUID id)  {
        String sql = "SELECT * FROM users_info WHERE id = ?";

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnID = UUID.fromString(resultSet.getString("id"));
                String columnName = resultSet.getString("name");
                LocalDate columnCreationDate = Date.valueOf(resultSet.getString("creation_date")).toLocalDate();

                switch (resultSet.getString("user_role")) {
                    case "CLIENT" :
                        return Optional.of(new Client(columnID, columnName, columnCreationDate));
                    case "ADMIN" :
                        return Optional.of(new Admin(columnID, columnName, columnCreationDate));
                }

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

    @Override
    public ArrayList<User> getAll() {
        String sql = "SELECT * FROM users_info";

        users.clear();

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UUID columnID = UUID.fromString(resultSet.getString("id"));
                String columnName = resultSet.getString("name");
                LocalDate columnCreationDate = Date.valueOf(resultSet.getString("creation_date")).toLocalDate();
                UserRole columnRole = UserRole.valueOf(resultSet.getString("user_role"));

                switch (columnRole) {
                    case CLIENT -> users.add(new Client(columnID, columnName, columnCreationDate));
                    case ADMIN -> users.add(new Admin(columnID, columnName, columnCreationDate));
                }
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
        return users;
    }


    @Override
    public void delete(UUID userID) {
        String sql = "DELETE FROM users_info WHERE id = ?";
        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID.toString());
            preparedStatement.executeUpdate();
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
    }

    @Override
    public void printInformation() {
        users = this.getAll();

        System.out.println("=========================== TABLE 'USERS_INFO' ==========================\n" +
                "                  id                 |  name  | creation_date | user_role  \n" +
                "--------------------------------------------------------------");
        if (users.isEmpty()) {
            System.out.println("empty\n");
        } else {
            for (User u: users) {
                System.out.println(u.getID() + " | " + u.getName() + " | " + u.getCreationDate() + " | " + u.getRole());
            }
            System.out.print('\n');
        }
    }
}
