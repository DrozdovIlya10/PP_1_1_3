package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }
    PreparedStatement preparedStatement;
    public void createUsersTable()  {

        try (Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement ("DROP TABLE IF EXISTS `user`;");

            preparedStatement = connection.prepareStatement("CREATE TABLE user (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    lastname VARCHAR(255) NOT NULL,\n" +
                    "    age INT NOT NULL\n" +
                    ");"
            );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
        }
    }

    public void dropUsersTable()  {
        try(Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement ("DROP TABLE IF EXISTS `user`;");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age)  {
        try(Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement ("INSERT INTO user (name,lastname,age) VALUES ('"
                    + name + "', '"+ lastName +"', '"+ age+ "' )");
            preparedStatement.executeUpdate();
            System.out.println("User c именем - "+ name + " добавлени в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя");
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = getConnection()) {

            preparedStatement = connection.prepareStatement ( "DELETE FROM user WHERE id = " + id + ";");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выводе всех пользователей");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement ("TRUNCATE TABLE user;");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы user");
        }
    }
}
