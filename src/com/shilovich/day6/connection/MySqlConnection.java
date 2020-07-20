package com.shilovich.day6.connection;

import com.shilovich.day6.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection {
    private static MySqlConnection instance;

    public static synchronized MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() throws DaoException {
        String url = "jdbc:mysql://localhost:3306/books";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "root");
        properties.put("useUnicode", "true");
        properties.put("serverTimezone", "UTC");
        properties.put("characterEncoding", "UTF-8");
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new DaoException("Connection Exception");
        }
    }
}
