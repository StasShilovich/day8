package com.shilovich.day8.model.dao;

import com.shilovich.day8.exception.DaoException;
import com.shilovich.day8.model.entity.CustomBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface CustomBookListDao {
    void add(CustomBook book) throws DaoException;

    void delete(Long id) throws DaoException;

    void update(CustomBook book) throws DaoException;

    List<CustomBook> findAll() throws DaoException;

    CustomBook find(Long id) throws DaoException;

    List<CustomBook> sortBookByParameter(String parameter) throws DaoException;

    default void close(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException("Connection close fail!", e);
            }
        }
    }

    default void close(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Statement close fail!", e);
            }
        }
    }

    default void close(ResultSet set) throws DaoException {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                throw new DaoException("ResultSet close fail!", e);
            }
        }
    }
}
