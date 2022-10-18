package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String NAME_USER = "root";
    private static final String PASSWORD = "Qwerty-123";
    private static final String URL = "jdbc:mysql://localhost:3306/test1";
    private static Connection connection;
    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
        return connection;
    }


}
