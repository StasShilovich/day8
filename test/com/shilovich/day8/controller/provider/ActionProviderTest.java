package com.shilovich.day8.controller.provider;

import com.shilovich.day8.controller.command.impl.AddBookCommand;
import com.shilovich.day8.exception.ControllerException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ActionProviderTest {
    ActionProvider provider;

    @BeforeClass
    public void setUp() {
        provider = new ActionProvider();
    }

    @Test
    public void testDefineCommandPositive() throws ControllerException {
        String command = "add_book_command";
        var actual = provider.defineCommand(command);
        var expected = new AddBookCommand();
        assertEquals(actual.getClass(), expected.getClass());
    }

    @Test
    public void testDefineCommandNegative() throws ControllerException {
        String command = "sort_by_parameter";
        var actual = provider.defineCommand(command);
        var expected = new AddBookCommand();
        assertNotEquals(actual.getClass(), expected.getClass());
    }

    @Test(expectedExceptions = ControllerException.class)
    public void testDefineCommandException() throws ControllerException {
        String command = "sort_by_tag_comand";
        provider.defineCommand(command);
    }
}