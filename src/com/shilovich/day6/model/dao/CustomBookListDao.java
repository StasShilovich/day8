package com.shilovich.day6.model.dao;

import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.model.entity.CustomBook;

import java.util.List;
import java.util.Optional;

public interface CustomBookListDao {
    boolean add(CustomBook book) throws DaoException;

    boolean delete(CustomBook book) throws DaoException;

    boolean update(CustomBook book) throws DaoException;

    Optional<CustomBook> find(int id) throws DaoException;

    CustomBook findByTag(int tag);

    List<CustomBook> findAll() throws DaoException;

    List<CustomBook> sortBookByAuthor();

    List<CustomBook> sortBookByYear();

    List<CustomBook> sortBookByPrice();
}
