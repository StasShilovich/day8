package com.shilovich.day8.model.dao.impl;

import com.shilovich.day8.exception.DaoException;
import com.shilovich.day8.model.connection.MySqlConnection;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.model.dao.CustomBookListDao;
import com.shilovich.day8.model.entity.CustomBookSortEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.shilovich.day8.model.constant.CustomBookConstant.*;

public class CustomBookListDaoImpl implements CustomBookListDao {
    private static final String ADD_BOOK_SQL = "INSERT INTO book(author,title,year,price,deleted) VALUES (?,?,?,?,?)";
    private static final String DELETE_BOOK_SQL = "UPDATE book SET deleted=0 WHERE id=?";
    private static final String UPDATE_BOOK_SQL = "UPDATE book SET author=?, title=?, year=?, price=?, deleted=? WHERE id=?";
    private static final String FIND_ALL_BOOKS_SQL = "SELECT id,author,title,year,price FROM book WHERE deleted=0";
    private static final String FIND_BOOK_SQL = "SELECT id,author,title,year,price FROM book WHERE deleted=0 AND id=?";
    private static final String SORT_BOOK_SQL = "SELECT id,author,title,year,price FROM book WHERE deleted=0 ORDER BY ";
    private static CustomBookListDaoImpl instance;

    private CustomBookListDaoImpl() {
    }

    public static CustomBookListDaoImpl getInstance() {
        if (instance == null) {
            instance = new CustomBookListDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(CustomBook book) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.prepareStatement(ADD_BOOK_SQL);
            setPrepareStatementParameters(statement, book);
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Adding book. No rows affected");
            }
        } catch (SQLException e) {
            throw new DaoException("Dao add fail!", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_BOOK_SQL);
            statement.setString(1, id.toString());
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Deleting book. No rows affected");
            }
        } catch (SQLException e) {
            throw new DaoException("Dao delete fail!", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void update(CustomBook book) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.prepareStatement(UPDATE_BOOK_SQL);
            setPrepareStatementParameters(statement, book);
            statement.setString(6, book.getId().toString());
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Updating book. No rows affected");
            }
        } catch (SQLException e) {
            throw new DaoException("Dao update fail!", e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<CustomBook> findAll() throws DaoException {
        List<CustomBook> bookList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(FIND_ALL_BOOKS_SQL);
            while (set.next()) {
                CustomBook book = new CustomBook();
                receiveBookFields(book, set);
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Dao find all fail!", e);
        } finally {
            close(set);
            close(statement);
            close(connection);
        }
        return bookList;
    }

    @Override
    public CustomBook find(Long id) throws DaoException {
        CustomBook book = new CustomBook();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.prepareStatement(FIND_BOOK_SQL);
            statement.setString(1, id.toString());
            set = statement.executeQuery();
            while (set.next()) {
                receiveBookFields(book, set);
            }
        } catch (SQLException e) {
            throw new DaoException("Dao find by id fail!", e);
        } finally {
            close(set);
            close(statement);
            close(connection);
        }
        return book;
    }

    @Override
    public List<CustomBook> sortBookByParameter(String parameter) throws DaoException {
        List<CustomBook> bookList = new ArrayList<>();
        CustomBookSortEnum sortEnum;
        try {
            sortEnum = CustomBookSortEnum.valueOf(parameter.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DaoException("Sort enum illegal argument!", e);
        }
        String sortParameter = sortEnum.getSortParameter();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            connection = MySqlConnection.getInstance().getConnection();
            statement = connection.createStatement();
            String sql = SORT_BOOK_SQL.concat(sortParameter);
            set = statement.executeQuery(sql);
            while (set.next()) {
                CustomBook book = new CustomBook();
                receiveBookFields(book, set);
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Dao sort book fail!", e);
        } finally {
            close(set);
            close(statement);
            close(connection);
        }
        return bookList;
    }

    private void receiveBookFields(CustomBook book, ResultSet set) throws SQLException {
        book.setId(set.getLong(ID));
        book.setAuthor(set.getString(AUTHOR));
        book.setTitle(set.getString(TITLE));
        book.setYear(set.getInt(YEAR));
        book.setPrice(set.getBigDecimal(PRICE));
    }

    private void setPrepareStatementParameters(PreparedStatement statement, CustomBook book) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getYear().toString());
        statement.setString(4, book.getPrice().toString());
        statement.setString(5, book.getDeleted().toString());
    }
}