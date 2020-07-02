package Homework15.task1.ConnectionManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManagerIMPL implements DBManager {

    public static final DBManager INSTANCE = new DBManagerIMPL();

    public static DBManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection(String s, String postgres, String s1) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(s, postgres, s1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/innopolis", "postgres", "11111");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
