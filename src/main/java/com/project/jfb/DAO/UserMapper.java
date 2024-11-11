package DAO;

import model.Admin;
import model.Client;
import model.User;
import model.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = null;
        switch (UserRole.valueOf(rs.getString("user_role"))) {
            case ADMIN ->
                    user = new Admin(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getTimestamp("creation_date"));
            case CLIENT ->
                user = new Client(UUID.fromString(rs.getString("id")), rs.getString("name"), rs.getTimestamp("creation_date"));
        }
        return user;
    }
}
