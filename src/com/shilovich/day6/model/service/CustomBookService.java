package com.shilovich.day6.model.service;

import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.entity.CustomBook;

import java.util.List;

public interface CustomBookService {
    void addBook(List<String> book) throws ServiceException;

    void removeBook(List<String> book) throws ServiceException;

    CustomBook findByTag(String tag);

    List<CustomBook> findAll() throws ServiceException;

    List<CustomBook> sortBookByAuthor();

    List<CustomBook> sortBookByYear();

    List<CustomBook> sortBookByPrice();
}
