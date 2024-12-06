package jm.task.core.hiber.dao;

import jm.task.core.hiber.model.User;
import jm.task.core.hiber.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS Users";

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS Users (
                Id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                lastName VARCHAR(100),
                age TINYINT
            )""";

    private static final String SAVE_USER = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";

    private static final String GET_ALL_USER = "SELECT * FROM Users";

    private static final String REMOVE_USER_BY_ID = "DELETE FROM Users WHERE Id=?";

    private static final String CLEAN_USERS_TABLE = "DELETE FROM Users";

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().execute(CREATE_TABLE);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error creating table: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().execute(DROP_TABLE);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error when deleting table: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)
        ) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем " + name + " добавлен в базу данных!");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error when saving user: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error when deleting user: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USER);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));

                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error getting all users: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            connection.createStatement().execute(CLEAN_USERS_TABLE);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error when clearing table: " + e.getMessage());
        }
    }
}