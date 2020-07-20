package test.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.impl.RemoveBookCommand;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.shilovich.day6.creator.TestStorageCreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class RemoveBookCommandTest {
    CustomBookStorage storage;
    ActionCommand<String, Boolean, List<String>> command;
    CustomBook book;

    @BeforeClass
    public void setUp() {
        storage = new TestStorageCreator().addBooks();
        command = new RemoveBookCommand();
        book = new CustomBook(30741, "Jonathan Coe", "What a Carve Up!",
                1944, new BigDecimal("14.89"));
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
        List<String> strings = new ArrayList<>();
        strings.add("30741");
        strings.add("Jonathan Coe");
        strings.add("What a Carve Up!");
        strings.add("1944");
        strings.add("14.89");
        request.put("Books", strings);
        Map<String, Boolean> actual = command.execute(request);
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("isRemoved", true);
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testExecutePositive", priority = 1)
    public void modifyPreviousProcess() {
        storage.setBook(book);
    }

    @Test
    public void testExecuteNegative() {
        Map<String, List<String>> request = new HashMap<>();
        List<String> strings = new ArrayList<>();
        strings.add("30741");
        strings.add("Jonathan Coe");
        strings.add("What a Carve Up!");
        strings.add("2077");
        strings.add("14.89");
        request.put("Books", strings);
        Map<String, Boolean> actual = command.execute(request);
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("isRemoved", false);
        assertEquals(actual, expected);
    }
}