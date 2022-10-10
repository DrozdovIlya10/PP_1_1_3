package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String NAMEUSER = "root";
    private static final String PASSWORD = "Qwerty-123";
    private static final String URL = "jdbc:mysql://localhost:3306/test1";
    private static Connection connection;
    private Statement statement;
    public Statement Connection() {
        try {
            connection = DriverManager.getConnection(URL, NAMEUSER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e ) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return statement;
    }


}
