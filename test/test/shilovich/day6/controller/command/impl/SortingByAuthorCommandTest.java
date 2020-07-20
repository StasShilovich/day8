package test.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.impl.SortingByAuthorCommand;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class SortingByAuthorCommandTest {
    ActionCommand<String, List<CustomBook>, String> command;
    CustomBookStorage storage;
    CustomBook book1;
    CustomBook book2;
    CustomBook book3;

    @BeforeClass
    public void setUp() {
        command = new SortingByAuthorCommand();
        storage = CustomBookStorage.getInstance();
        List<CustomBook> books = new ArrayList<>();
        book1 = new CustomBook(41554, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"));
        book2 = new CustomBook(96942, "Albert Camus", "The Plague",
                1947, new BigDecimal("12.49"));
        book3 = new CustomBook(59725, "Shashi Tharoor", "The Great Indian Novel",
                1989, new BigDecimal("24.36"));
        books.add(book1);
        books.add(book2);
        books.add(book3);
        storage.setBooks(books);
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test
    public void testExecutePositive() {
        Map<String, List<CustomBook>> actual = command.execute(new HashMap<>());
        Map<String, List<CustomBook>> expected = new HashMap<>();
        List<CustomBook> list = new ArrayList<>();
        list.add(book2);
        list.add(book1);
        list.add(book3);
        expected.put("Books", list);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteNegative() {
        Map<String, List<CustomBook>> actual = command.execute(new HashMap<>());
        Map<String, List<CustomBook>> expected = new HashMap<>();
        List<CustomBook> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        expected.put("Books", list);
        assertNotEquals(actual, expected);
    }
}