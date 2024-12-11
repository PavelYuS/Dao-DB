package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbcata";
    private static final String USERNAME = "root";
    private static final String Password = "root";
    //    private Connection connection;

    public Util() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(HOST, USERNAME, Password);
//            System.out.println("Есть коннект");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Не удалось подключиться");

        }
        return connection;
    }
}
