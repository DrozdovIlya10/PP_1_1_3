package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static final String NAMEUSER = "root";
    public static final String PASSWORD = "Qwerty-123";
    public static final String URL = "jdbc:mysql://localhost:3306/test1";
    public static Connection connection;
    public static Statement statement;
    public void Connection() {
        String url = "jdbc:mysql://localhost:3306/test1";
        String username = "root";
        String password = "Qwerty-123";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e ) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }


}
