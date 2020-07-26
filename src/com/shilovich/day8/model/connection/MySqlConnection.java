package com.shilovich.day8.model.connection;

import com.shilovich.day8.exception.DaoException;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static com.shilovich.day8.model.connection.ConnectionManager.*;

public class MySqlConnection {
    private static MySqlConnection instance;
    private static BasicDataSource source = new BasicDataSource();

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    static {
        ConnectionManager manager = ConnectionManager.getInstance();
        source.setUrl(manager.getProperty(URL));
        source.setUsername(manager.getProperty(USERNAME));
        source.setPassword(manager.getProperty(PASSWORD));
        source.setMinIdle(Integer.parseInt(manager.getProperty(MIN_IDLE)));
        source.setMaxIdle(Integer.parseInt(manager.getProperty(MAX_IDLE)));
        source.setMaxOpenPreparedStatements(Integer.parseInt(manager.getProperty(MAX_OPEN_PREPARED_STATEMENTS)));
    }

    public Connection getConnection() throws DaoException {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            throw new DaoException("Connection Exception", e);
        }
    }
}
