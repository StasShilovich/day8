package com.shilovich.day8.model.parser;

import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class StringToCustomBookParserTest {
    StringToCustomBookParser parser;

    @BeforeClass
    public void setUp() {
        parser = new StringToCustomBookParser();
    }

    @Test
    public void testBookParserPositive() throws ServiceException {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("Author");
        strings.add("Title");
        strings.add("1995");
        strings.add("20.2");
        strings.add("true");
        CustomBook actual = parser.parseToBook(strings);
        CustomBook expected = new CustomBook(1L, "Author", "Title", 1995,
                new BigDecimal("20.2"), true);
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testBookParserNegative() throws ServiceException {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("Author");
        strings.add("Title");
        strings.add("1995");
        strings.add("20.2n");
        strings.add("true");
        parser.parseToBook(strings);
    }
}