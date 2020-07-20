package test.shilovich.day6.model.dao.impl;

import com.shilovich.day6.exception.DaoException;
import com.shilovich.day6.model.dao.CustomBookListDao;
import com.shilovich.day6.model.dao.impl.CustomBookListDaoImpl;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.shilovich.day6.creator.TestStorageCreator;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class CustomBookListDaoImplTest {
    CustomBookStorage storage;
    CustomBookListDao dao;
    CustomBook addBook;
    CustomBook existBook;
    CustomBook returnBook;
    CustomBook removedBookNegative;

    @BeforeClass
    public void setUp() {
        storage = new TestStorageCreator().addBooks();
        dao = CustomBookListDaoImpl.getInstance();
        addBook = new CustomBook(47965, "W.G. Sebald", "Vertigo",
                1990, new BigDecimal("9.49"));
        existBook = new CustomBook(96942, "Albert Camus", "The Plague",
                1947, new BigDecimal("12.49"));
        removedBookNegative = new CustomBook(11111, "Jonathan Coe", "What a Carve Up!",
                1944, new BigDecimal("14.89"));
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test(priority = 1)
    public void testAddBookPositive() throws DaoException {
        dao.addBook(addBook);
        CustomBook actual = addBook;
        CustomBook expected = storage.getBook(storage.size() - 1);
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testAddBookPositive", priority = 1)
    public void modifyPreviousAdd() {
        boolean condition = storage.deleteBook(storage.size() - 1);
        assertTrue(condition);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testAddBookNegative() throws DaoException {
        dao.addBook(existBook);
        fail("No exception was thrown!");
    }

    @Test(priority = 1)
    public void testRemoveBookPositive() throws DaoException {
        CustomBook removedBook = storage.getBook(storage.size() - 1);
        returnBook = removedBook;
        dao.removeBook(removedBook);
        CustomBook storageBook = storage.getBook(storage.size() - 1);
        assertNotEquals(removedBook, storageBook);
    }

    @Test(dependsOnMethods = "testRemoveBookPositive", priority = 1)
    public void modifyPreviousRemove() {
        boolean condition = storage.setBook(returnBook);
        assertTrue(condition);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testRemoveBookNegative() throws DaoException {
        dao.removeBook(removedBookNegative);
        fail("No exception was thrown!");
    }

    @Test
    public void testFindByTagPositive() {
        CustomBook actual = dao.findByTag(96942);
        CustomBook expected = existBook;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTagNegative() {
        CustomBook actual = dao.findByTag(47965);
        CustomBook expected = existBook;
        assertNotEquals(actual, expected);
    }
}