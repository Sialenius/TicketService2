package DAO;

import config.ConfigurationObject;
import config.ConnectionConfiguration;
import model.Admin;
import model.Client;
import model.User;
import model.enums.UserRole;
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
public class UserDAO implements DAO<User>, Printable {
    private static final String DELETE_ALL_USERS_SQL = "DELETE FROM users_info";
    private static final String SAVE_USER_SQL = "INSERT INTO users_info (id, name, creation_date, user_role) VALUES (?, ?, ?, ?::user_role)";
    private static final String FETCH_USER_BY_ID_SQL = "SELECT * FROM users_info WHERE id = ?";
    private static final String FETCH_ALL_USERS_SQL = "SELECT * FROM users_info";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users_info WHERE id = ?";

    private Connection connection;
    
    private ArrayList<User> users = new ArrayList<>();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private ConfigurationObject configuration;

    private DataSource dataSource;

    public UserDAO(ConfigurationObject configuration) {
        this.configuration = configuration;
    }

    public UserDAO(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteAll() {
        try {
            connection = dataSource.getConnection();
            System.out.println(connection);
            preparedStatement = connection.prepareStatement(DELETE_ALL_USERS_SQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
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
    public void save(User user) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SAVE_USER_SQL);
            preparedStatement.setString(1, user.getID().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setTimestamp(3, user.getCreationDate());
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
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FETCH_USER_BY_ID_SQL);
            preparedStatement.setString(1, id.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UUID columnID = UUID.fromString(resultSet.getString("id"));
                String columnName = resultSet.getString("name");
                Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");

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
        users.clear();

        try {
            connection = dataSource.getConnection();

            preparedStatement = connection.prepareStatement(FETCH_ALL_USERS_SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UUID columnID = UUID.fromString(resultSet.getString("id"));
                String columnName = resultSet.getString("name");
                Timestamp columnCreationDate = resultSet.getTimestamp("creation_date");
               /* UserRole columnRole = UserRole.valueOf(resultSet.getString("user_role"));

                switch (columnRole) {
                    case CLIENT -> users.add(new Client(columnID, columnName, columnCreationDate));
                    case ADMIN -> users.add(new Admin(columnID, columnName, columnCreationDate));
                }

                */
                users.add(new Client(columnID, columnName, columnCreationDate));
            }

        } catch (SQLException e) {
           // System.out.println(e);
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
        return users;
    }


    @Override
    public void delete(UUID userID) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL);
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
