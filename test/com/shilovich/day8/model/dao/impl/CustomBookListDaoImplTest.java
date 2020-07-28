package com.shilovich.day8.model.dao.impl;

import com.shilovich.day8.exception.DaoException;
import com.shilovich.day8.model.dao.CustomBookListDao;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.provider.StaticDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CustomBookListDaoImplTest {
    CustomBookListDao dao;

    @BeforeClass
    public void setUp() {
        dao = CustomBookListDaoImpl.getInstance();
    }

    @Test(dataProvider = "Books", dataProviderClass = StaticDataProvider.class)
    public void testFindAllPositive(Object[] expected) throws DaoException {
        List<CustomBook> books = dao.findAll();
        Object[] actual = new Object[]{books};
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "Books", dataProviderClass = StaticDataProvider.class)
    public void testFindAllNegative(Object[] expected) {
        assertNotEquals(new Object[]{}, expected);
    }

    @Test(dataProvider = "FindById", dataProviderClass = StaticDataProvider.class)
    public void testFindById(Long id, CustomBook book) throws DaoException {
        CustomBook customBook = dao.find(id);
        assertEquals(customBook, book);
    }

    @Test(dataProvider = "SortByAuthor", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByAuthorPositive(Object[] expected) throws DaoException {
        List<CustomBook> books = dao.sortBookByParameter("author");
        Object[] actual = new Object[]{books};
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "SortByAuthor", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByAuthorNegative(Object[] expected) throws DaoException {
        assertNotEquals(new Object[]{}, expected);
    }

    @Test(dataProvider = "SortByPrice", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByPricePositive(Object[] expected) throws DaoException {
        List<CustomBook> books = dao.sortBookByParameter("price");
        Object[] actual = new Object[]{books};
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "SortByPrice", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByPriceNegative(Object[] expected) throws DaoException {
        assertNotEquals(new Object[]{}, expected);
    }

    @Test(dataProvider = "SortByYear", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByYearPositive(Object[] expected) throws DaoException {
        List<CustomBook> books = dao.sortBookByParameter("year");
        Object[] actual = new Object[]{books};
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "SortByYear", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByYearNegative(Object[] expected) throws DaoException {
        assertNotEquals(new Object[]{}, expected);
    }

    @Test(dataProvider = "SortByTitle", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByTitlePositive(Object[] expected) throws DaoException {
        List<CustomBook> books = dao.sortBookByParameter("year");
        Object[] actual = new Object[]{books};
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "SortByTitle", dataProviderClass = StaticDataProvider.class)
    public void testSortBooksByTitleNegative(Object[] expected) throws DaoException {
        assertNotEquals(new Object[]{}, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testSortBooksByParameter() throws DaoException {
        dao.sortBookByParameter("titel");
    }
}