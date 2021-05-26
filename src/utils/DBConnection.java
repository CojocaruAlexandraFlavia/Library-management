package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pao";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private final Connection connection;

    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor();
            this.connection = getConnection();
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException exception) {
            throw  new RuntimeException("Something went wrong during connection/driver invocation.");
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    private static final class SINGLETON_HOLDER{
        private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DBConnection getInstance(){
        return SINGLETON_HOLDER.INSTANCE;
    }

    public Connection getDbConnection(){
        return this.connection;
    }

}
