package test.shilovich.day6.model.service.impl;

import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import com.shilovich.day6.model.service.CustomBookService;
import com.shilovich.day6.model.service.impl.CustomBookServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.shilovich.day6.creator.TestStorageCreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class CustomBookServiceImplTest {
    CustomBookStorage storage;
    CustomBookService service;
    CustomBook expectedBook;
    List<String> book;
    List<String> notValidBook;
    List<String> removedBook;

    @BeforeClass
    public void setUp() {
        storage = new TestStorageCreator().addBooks();
        service = CustomBookServiceImpl.getInstance();
        expectedBook = new CustomBook(30741, "Jonathan Coe", "What a Carve Up!",
                1944, new BigDecimal("14.89"));
        book = new ArrayList<>();
        book.add("11111");
        book.add("author");
        book.add("title");
        book.add("2020");
        book.add("20");
        notValidBook = new ArrayList<>();
        notValidBook.add("96942");
        notValidBook.add("Albert Camus");
        notValidBook.add("The Plague");
        notValidBook.add("1947");
        notValidBook.add("12.49n");
        removedBook = new ArrayList<>();
        removedBook.add("30741");
        removedBook.add("Jonathan Coe");
        removedBook.add("What a Carve Up!");
        removedBook.add("1944");
        removedBook.add("14.89");
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test(priority = 1)
    public void testAddBookPositive() throws ServiceException {
        service.addBook(book);
        CustomBook actual = service.findByTag("11111");
        CustomBook expected = new CustomBook(11111, "author", "title", 2020, new BigDecimal("20"));
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testAddBookPositive", priority = 1)
    public void modifyPreviousAdd() throws ServiceException {
        boolean condition = storage.deleteBook(storage.size() - 1);
        assertTrue(condition);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddBookNegative() throws ServiceException {
        service.addBook(notValidBook);
        fail("No exception was thrown!");
    }

    @Test(priority = 1)
    public void testRemoveBookPositive() throws ServiceException {
        int before = storage.size();
        service.removeBook(removedBook);
        int after = storage.size();
        assertEquals(before, after + 1);
    }

    @Test(dependsOnMethods = "testRemoveBookPositive", priority = 1)
    public void modifyPreviousRemove() throws ServiceException {
        int before = storage.size();
        service.addBook(removedBook);
        int after = storage.size();
        assertEquals(before + 1, after);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRemoveBookNegative() throws ServiceException {
        service.removeBook(notValidBook);
        fail("No exception was thrown!");
    }

    @Test
    public void testFindByTagPositive() {
        CustomBook actual = service.findByTag("30741");
        CustomBook expected = expectedBook;
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTagNegative() {
        CustomBook actual = service.findByTag("30742n");
        CustomBook expected = expectedBook;
        assertNotEquals(actual, expected);
    }
}