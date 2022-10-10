package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        Statement statement = Connection();
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS `user`;");
            statement.executeUpdate("CREATE TABLE user (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    lastname VARCHAR(255) NOT NULL,\n" +
                    "    age INT NOT NULL\n" +
                    ");"
            );
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable()  {
        Statement statement = Connection();
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS `user`;");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age)  {
        Statement statement = Connection();
        try {
            statement.executeUpdate("INSERT INTO user (name,lastname,age) VALUES ('"
                    + name + "', '"+ lastName +"', '"+ age+ "' )");
            System.out.println("User c именем - "+ name + " добавлени в базу данных");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя");
        }
    }

    public void removeUserById(long id) {
        Statement statement = Connection();
        try {
            statement.executeUpdate( "DELETE FROM user WHERE id = " + id + ";");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        Statement statement = Connection();
        ResultSet resultSet;
        List<User> list = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));
                list.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе всех пользователей");
        }

        return list;
    }

    public void cleanUsersTable() {
        Statement statement = Connection();
        try {
            statement.executeUpdate("TRUNCATE TABLE user;");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
