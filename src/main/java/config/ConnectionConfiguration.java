package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    public static Connection getConnection(ConfigurationObject configuration) throws SQLException {
        return DriverManager.getConnection(configuration.getUrl(), configuration.getLogin(), configuration.getPassword());
    }

}
