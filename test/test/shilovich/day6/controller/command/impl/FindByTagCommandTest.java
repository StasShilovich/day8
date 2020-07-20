package test.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.impl.FindByTagCommand;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.entity.CustomBookStorage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.shilovich.day6.creator.TestStorageCreator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class FindByTagCommandTest {
    CustomBookStorage storage;
    ActionCommand<String, CustomBook, String> command;
    CustomBook book;

    @BeforeClass
    public void setUp() {
        storage = new TestStorageCreator().addBooks();
        command = new FindByTagCommand();
        book = new CustomBook(30741, "Jonathan Coe", "What a Carve Up!",
                1944, new BigDecimal("14.89"));
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test
    public void testExecutePositive() {
        Map<String, String> request = new HashMap<>();
        request.put("Tag", "30741");
        Map<String, CustomBook> actual = command.execute(request);
        Map<String, CustomBook> expected = new HashMap<>();
        expected.put("Book", book);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteNegative() {
        Map<String, String> request = new HashMap<>();
        request.put("Tag", "30742");
        Map<String, CustomBook> actual = command.execute(request);
        Map<String, CustomBook> expected = new HashMap<>();
        expected.put("Book", new CustomBook());
        assertEquals(actual, expected);
    }
}