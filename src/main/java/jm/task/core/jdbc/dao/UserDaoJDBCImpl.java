package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {

        String createTableSql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age TINYINT)";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(createTableSql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String dropTableSql = "DROP TABLE IF EXISTS users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(dropTableSql);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSQL = "INSERT INTO mydbcata.users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(saveSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем -  " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeSQL = "DELETE FROM mydbcata.users WHERE id = ?";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(removeSQL)) {
            preparedStatement.setLong(1, id);


            preparedStatement.executeUpdate();
            System.out.println("Пользователь удалён");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String allUsers = "SELECT * FROM mydbcata.users";

        try (Statement statement = Util.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(allUsers);
            System.out.println("Получены все пользователи");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String clnUsersTable = "TRUNCATE TABLE mydbcata.users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(clnUsersTable);

            System.out.println("Все пользователи из таблицы удалены");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
