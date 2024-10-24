package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
    private static final String URL = "jdbc:postgresql://localhost:5432/TicketServiceDB";;
    private static final String USER_NAME ="postgres";
    private static final String PASSWORD = "123";


    public ConnectionConfiguration() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD );
    }


}
