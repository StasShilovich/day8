package com.shilovich.day6.model.dao.impl;

import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import com.shilovich.day6.model.dao.CustomBookListDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomBookListDaoImpl implements CustomBookListDao {
    private static final int NOT_FOUND_INDEX = -1;
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
    public void addBook(CustomBook book) throws DaoException {
        if (findCustomBookIndex(book) == NOT_FOUND_INDEX) {
            CustomBookStorage.getInstance().setBook(book);
        } else {
            throw new DaoException("Book is already exist! " + book.toString());
        }
    }

    @Override
    public void removeBook(CustomBook book) throws DaoException {
        int index = findCustomBookIndex(book);
        if (index != NOT_FOUND_INDEX) {
            CustomBookStorage.getInstance().deleteBook(index);
        } else {
            throw new DaoException("This book is not in storage! " + book.toString());
        }
    }

    @Override
    public CustomBook findByTag(int tag) {
        CustomBook result = new CustomBook();
        boolean isExist = false;
        int size = CustomBookStorage.getInstance().size();
        for (int i = 0; i < size; i++) {
            CustomBook book = CustomBookStorage.getInstance().getBook(i);
            if (book.getTag() == tag) {
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
    public List<CustomBook> sortBookByTag(Connection connection) throws DaoException {
        String sql = "SELECT id,author,title,year,price FROM BOOK";
        List<CustomBook> bookList = new ArrayList<>();
        try (ResultSet set = connection.createStatement().executeQuery(sql)) {
            while (set.next()) {
                CustomBook book = new CustomBook();
                book.setTag(set.getInt("id"));
                book.setAuthor(set.getString("author"));
                book.setTitle(set.getString("title"));
                book.setYear(set.getInt("year"));
                book.setPrice(set.getBigDecimal("price"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
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

    private int findCustomBookIndex(CustomBook book) throws DaoException {
        int index = NOT_FOUND_INDEX;
        int size = CustomBookStorage.getInstance().size();
        for (int i = 0; i < size; i++) {
            CustomBook existBook = CustomBookStorage.getInstance().getBook(i);
            if (existBook.getTag() == book.getTag()) {
                index = i;
            }
        }
        return index;
    }
}
