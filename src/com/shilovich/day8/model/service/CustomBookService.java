package com.shilovich.day8.model.service;

import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;

import java.util.List;

public interface CustomBookService {
    void add(List<String> book) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void update(List<String> book) throws ServiceException;

    List<CustomBook> findAll() throws ServiceException;

    CustomBook find(Long id) throws ServiceException;

    List<CustomBook> sortBookByParameter(String parameter) throws ServiceException;
}