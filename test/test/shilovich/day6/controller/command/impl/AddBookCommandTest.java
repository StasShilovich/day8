package test.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.impl.AddBookCommand;
import com.shilovich.day6.model.entity.CustomBookStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.shilovich.day6.creator.TestStorageCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class AddBookCommandTest {
    CustomBookStorage storage;
    ActionCommand<String, Boolean, List<String>> command;

    @BeforeClass
    public void setUp() {
        storage = new TestStorageCreator().addBooks();
        command = new AddBookCommand();
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test(priority = 1)
    public void testExecutePositive() {
        Map<String, List<String>> request = new HashMap<>();
        List<String> book = new ArrayList<>();
        book.add("11111");
        book.add("author");
        book.add("title");
        book.add("2020");
        book.add("20");
        request.put("Book", book);
        Map<String, Boolean> actual = command.execute(request);
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("isAdd", true);
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testExecutePositive", priority = 1)
    public void modifyPreviousProcess() {
        storage.deleteBook(storage.size() - 1);
    }

    @Test
    public void testExecuteNegative() {
        Map<String, List<String>> request = new HashMap<>();
        List<String> book = new ArrayList<>();
        book.add("11111");
        book.add("author");
        book.add("title");
        book.add("2020");
        book.add("20n");
        request.put("Book", book);
        Map<String, Boolean> actual = command.execute(request);
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("isAdd", true);
        assertNotEquals(actual, expected);
    }
}