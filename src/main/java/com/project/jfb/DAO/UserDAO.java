package DAO;

import model.User;
import model.enums.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import view.Printable;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class UserDAO implements DAO<User> {
    private static final String DELETE_ALL_USERS_SQL = "DELETE FROM users_info";
    private static final String SAVE_USER_SQL = "INSERT INTO users_info (id, name, creation_date, user_role) VALUES (?, ?, ?, ?::user_role)";
    private static final String FETCH_USER_BY_ID_SQL = "SELECT * FROM users_info WHERE id = ?";
    private static final String FETCH_USER_BY_NAME_SQL = "SELECT * FROM users_info WHERE name = ?";
    private static final String FETCH_ALL_USERS_SQL = "SELECT * FROM users_info";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users_info WHERE id = ?";
    private static final String UPDATE_USER_ID = "UPDATE users_info SET id = ? WHERE id = ?";


    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        return jdbcTemplate.query(FETCH_ALL_USERS_SQL, new UserMapper());
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SAVE_USER_SQL, user.getId(), user.getName(), user.getCreationDate(), user.getRole().name());
    }

    public void deleteAll() {
        jdbcTemplate.execute(DELETE_ALL_USERS_SQL);
    }

    public void updateUserID(UUID userID, UUID newID) {
        jdbcTemplate.update(UPDATE_USER_ID, newID.toString(), userID.toString());
    }

    @Override
    public User fetchByID(UUID id)  {
        return jdbcTemplate.query(FETCH_USER_BY_ID_SQL, new Object[]{id.toString()}, new UserMapper())
                .stream().findFirst().orElseThrow();
    }

    public User fetchByName(String name)  {
        return jdbcTemplate.query(FETCH_USER_BY_NAME_SQL, new Object[]{name}, new UserMapper())
                .stream().findFirst().orElseThrow();
    }

    @Override
    public void delete(UUID userID) {
        jdbcTemplate.update(DELETE_USER_BY_ID_SQL, userID.toString());

    }

}
