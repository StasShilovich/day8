package test.shilovich.day6.controller.invoker;

import com.shilovich.day6.controller.invoker.InvokeController;
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

public class InvokeControllerTest {
    InvokeController<String, List<CustomBook>, String> controller;
    CustomBookStorage storage;
    CustomBook book1;
    CustomBook book2;
    CustomBook book3;

    @BeforeClass
    public void setUp() {
        book1 = new CustomBook(41554, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"));
        book2 = new CustomBook(96942, "Albert Camus", "The Plague",
                1947, new BigDecimal("12.49"));
        book3 = new CustomBook(59725, "Shashi Tharoor", "The Great Indian Novel",
                1989, new BigDecimal("24.36"));
        storage = CustomBookStorage.getInstance();
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
        List<CustomBook> books = new ArrayList();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        storage.setBooks(books);
        controller = InvokeController.getInstance();
    }

    @AfterClass
    public void tearDown() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }

    @Test(priority = 1)
    public void testProcessRequestPositive() {
        Map<String, List<CustomBook>> actual = controller.processRequest("sort_by_author_command", new HashMap<>());
        List<CustomBook> sort = new ArrayList<>();
        sort.add(book2);
        sort.add(book1);
        sort.add(book3);
        Map<String, List<CustomBook>> expected = new HashMap<>();
        expected.put("Books", sort);
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testProcessRequestPositive", priority = 1)
    public void modifyPreviousProcess() {
        while (storage.size() >= 1) {
            storage.deleteBook(storage.size() - 1);
        }
    }
}