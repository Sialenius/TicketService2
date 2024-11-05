package DAO;

import model.User;
import model.enums.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setName(rs.getString("name"));
        user.setCreationDate(rs.getTimestamp("creation_date"));
        user.setRole(UserRole.valueOf(rs.getString("user_role")));

        return user;
    }
}
