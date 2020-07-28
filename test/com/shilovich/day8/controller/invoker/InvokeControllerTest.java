package com.shilovich.day8.controller.invoker;

import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.provider.StaticDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class InvokeControllerTest {
    InvokeController<String, List<CustomBook>, String> controller;

    @BeforeClass
    public void setUp() {
        controller = InvokeController.getInstance();
    }

    @Test(dataProvider = "Books", dataProviderClass = StaticDataProvider.class)
    public void testProcessRequestPositive(Object[] expected) {
        Map<String, List<CustomBook>> map = controller.processRequest("find_all_books_command", new HashMap<>());
        List<CustomBook> actual = map.get("Books");
        assertEquals(new Object[]{actual}, expected);
    }

    @Test
    public void testProcessRequestNegative() {
        Map<String, List<CustomBook>> map = controller.processRequest("find_all_books_command", new HashMap<>());
        List<CustomBook> actual = map.get("Books");
        assertNotEquals(new Object[]{actual}, new HashMap<>());
    }
}