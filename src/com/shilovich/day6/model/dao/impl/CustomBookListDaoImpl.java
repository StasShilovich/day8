package com.shilovich.day6.model.dao.impl;

import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.model.connection.MySqlConnection;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import com.shilovich.day6.model.dao.CustomBookListDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CustomBookListDaoImpl implements CustomBookListDao {
    private static final String ADD_SQL = "INSERT Book(author,title,year,price,deleted) VALUES (?,?,?,?,?)";
    private static final String DELETE_SQL = "";
    private static final String UPDATE_SQL = "";
    private static final String FIND_ALL_SQL = "SELECT id,author,title,year,price,deleted FROM BOOK WHERE deleted=0";
    private static CustomBookListDaoImpl instance;

    private CustomBookListDaoImpl() {
    }

    public static CustomBookListDaoImpl getInstance() {
        if (instance == null) {
            return new CustomBookListDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean add(CustomBook book) throws DaoException {
        boolean result = false;
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_SQL)) {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getYear().toString());
            statement.setString(4, book.getPrice().toString());
            statement.setString(5, book.getDeleted().toString());
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No rows affected");
            }
            result = true;
        } catch (SQLException e) {
            throw new DaoException("Dao add fail!", e);
        }
        return result;
    }

    @Override
    public boolean delete(CustomBook book) throws DaoException {
        boolean result = false;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {

        } catch (SQLException e) {
            throw new DaoException("Dao delete fail!", e);
        }
        return true;
    }

    @Override
    public boolean update(CustomBook book) throws DaoException {
        return false;
    }

    @Override
    public Optional<CustomBook> find(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public CustomBook findByTag(int tag) {
        CustomBook result = new CustomBook();
        boolean isExist = false;
        int size = CustomBookStorage.getInstance().size();
        for (int i = 0; i < size; i++) {
            CustomBook book = CustomBookStorage.getInstance().getBook(i);
            if (book.getId() == tag) {
                result = book;
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Book is not found!");
        }
        return result;
    }

    @Override
    public List<CustomBook> findAll() throws DaoException {
        List<CustomBook> bookList = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery(FIND_ALL_SQL)) {
            while (set.next()) {
                CustomBook book = new CustomBook();
                book.setId(set.getLong("id"));
                book.setAuthor(set.getString("author"));
                book.setTitle(set.getString("title"));
                book.setYear(set.getInt("year"));
                book.setPrice(set.getBigDecimal("price"));
                book.setDeleted(set.getBoolean("deleted"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException("Dao find all fail!", e);
        }
        return bookList;
    }

    @Override
    public List<CustomBook> sortBookByAuthor() {
        List<CustomBook> list = collectUnsortedList();
        list.sort(Comparator.comparing(CustomBook::getAuthor));
        return list;
    }

    @Override
    public List<CustomBook> sortBookByYear() {
        List<CustomBook> list = collectUnsortedList();
        list.sort(Comparator.comparing(CustomBook::getYear));
        return list;
    }

    @Override
    public List<CustomBook> sortBookByPrice() {
        List<CustomBook> list = collectUnsortedList();
        list.sort(Comparator.comparing(CustomBook::getPrice));
        return list;
    }

    private List<CustomBook> collectUnsortedList() {
        CustomBookStorage storage = CustomBookStorage.getInstance();
        List<CustomBook> result = new ArrayList<>();
        for (int i = 0; i < storage.size(); i++) {
            CustomBook book = storage.getBook(i);
            result.add(book);
        }
        return result;
    }
}
