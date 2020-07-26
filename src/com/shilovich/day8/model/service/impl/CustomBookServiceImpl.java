package com.shilovich.day8.model.service.impl;

import com.shilovich.day8.exception.DaoException;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.dao.impl.CustomBookListDaoImpl;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.model.parser.StringToCustomBookParser;
import com.shilovich.day8.model.service.CustomBookService;
import com.shilovich.day8.model.validator.CustomBookValidator;

import java.util.List;

public class CustomBookServiceImpl implements CustomBookService {
    private static CustomBookServiceImpl instance;

    private CustomBookServiceImpl() {
    }

    public static CustomBookServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomBookServiceImpl();
        }
        return instance;
    }

    public void add(List<String> books) throws ServiceException {
        StringToCustomBookParser parser = new StringToCustomBookParser();
        CustomBook customBook = parser.parseToBook(books);
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateCustomBook(customBook)) {
            throw new ServiceException("Book is not valid!");
        }
        try {
            CustomBookListDaoImpl.getInstance().add(customBook);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully add!");
    }

    public void delete(Long id) throws ServiceException {
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateId(id)) {
            throw new ServiceException("Book is not valid!");
        }
        try {
            CustomBookListDaoImpl.getInstance().delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully removed!");
    }

    @Override
    public void update(List<String> book) throws ServiceException {
        StringToCustomBookParser parser = new StringToCustomBookParser();
        CustomBook customBook = parser.parseToBook(book);
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateCustomBook(customBook)) {
            throw new ServiceException("Book is not valid!");
        }
        try {
            CustomBookListDaoImpl.getInstance().update(customBook);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully update!");
    }

    public List<CustomBook> findAll() throws ServiceException {
        List<CustomBook> books;
        try {
            books = CustomBookListDaoImpl.getInstance().findAll();
        } catch (DaoException e) {
            throw new ServiceException("Service find all fail!", e);
        }
        return books;
    }

    @Override
    public CustomBook find(Long id) throws ServiceException {
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateId(id)) {
            throw new ServiceException("Book is not valid!");
        }
        CustomBook book = new CustomBook();
        try {
            book = CustomBookListDaoImpl.getInstance().find(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        System.out.println("Book is successfully find!");
        return book;
    }

    @Override
    public List<CustomBook> sortBookByParameter(String parameter) throws ServiceException {
        CustomBookValidator validator = new CustomBookValidator();
        if (!validator.validateParameter(parameter)) {
            throw new ServiceException("Sort parameter not valid!");
        }
        List<CustomBook> books;
        try {
            books = CustomBookListDaoImpl.getInstance().sortBookByParameter(parameter.trim());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return books;
    }
}