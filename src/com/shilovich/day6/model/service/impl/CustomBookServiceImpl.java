package com.shilovich.day6.model.service.impl;

import com.shilovich.day6.connection.MySqlConnection;
import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.dao.impl.CustomBookListDaoImpl;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.parser.StringToCustomBookParser;
import com.shilovich.day6.model.service.CustomBookService;
import com.shilovich.day6.model.validator.CustomBookValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomBookServiceImpl implements CustomBookService {
    private static CustomBookServiceImpl instance;

    private CustomBookServiceImpl() {
    }

    public static CustomBookServiceImpl getInstance() {
        if (instance == null) {
            return new CustomBookServiceImpl();
        }
        return instance;
    }

    public void addBook(List<String> book) throws ServiceException {
        StringToCustomBookParser parser = new StringToCustomBookParser();
        CustomBook customBook = parser.parseToBook(book);
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateCustomBook(customBook)) {
            throw new ServiceException("Book is not valid!");
        }
        try {
            CustomBookListDaoImpl.getInstance().addBook(customBook);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully add!");
    }

    public void removeBook(List<String> book) throws ServiceException {
        StringToCustomBookParser parser = new StringToCustomBookParser();
        CustomBook customBook = parser.parseToBook(book);
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateCustomBook(customBook)) {
            throw new ServiceException("Book is not valid!");
        }
        try {
            CustomBookListDaoImpl.getInstance().removeBook(customBook);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully removed!");
    }

    public CustomBook findByTag(String tag) {
        StringToCustomBookParser parser = new StringToCustomBookParser();
        int parseTag = 0;
        try {
            parseTag = parser.parseToInt(tag);
        } catch (ServiceException e) {
            System.out.println("Tag is invalid!");
        }
        CustomBook book = CustomBookListDaoImpl.getInstance().findByTag(parseTag);
        return book;
    }

    public List<CustomBook> sortBookByTag() throws ServiceException {
        List<CustomBook> books = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            books = CustomBookListDaoImpl.getInstance().sortBookByTag(connection);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
        return books;
    }

    @Override
    public List<CustomBook> sortBookByAuthor() {
        return CustomBookListDaoImpl.getInstance().sortBookByAuthor();
    }

    @Override
    public List<CustomBook> sortBookByYear() {
        return CustomBookListDaoImpl.getInstance().sortBookByYear();
    }

    @Override
    public List<CustomBook> sortBookByPrice() {
        return CustomBookListDaoImpl.getInstance().sortBookByPrice();
    }
}
