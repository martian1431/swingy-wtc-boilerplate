package swingy.utils.database;

import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    @Nullable
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:Swingy.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
