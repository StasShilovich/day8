package com.shilovich.day6.model.dao;

import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.model.entity.CustomBook;

import java.sql.Connection;
import java.util.List;

public interface CustomBookListDao {
    void addBook(CustomBook book) throws DaoException;

    void removeBook(CustomBook book) throws DaoException;

    CustomBook findByTag(int tag);

    List<CustomBook> sortBookByTag(Connection connection) throws DaoException;

    List<CustomBook> sortBookByAuthor();

    List<CustomBook> sortBookByYear();

    List<CustomBook> sortBookByPrice();
}
