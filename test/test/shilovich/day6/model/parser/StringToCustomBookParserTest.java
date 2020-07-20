package test.shilovich.day6.model.parser;

import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.parser.StringToCustomBookParser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class StringToCustomBookParserTest {
    StringToCustomBookParser parser;
    List<String> strings;

    @BeforeMethod
    public void setUp() {
        parser = new StringToCustomBookParser();
        strings = new ArrayList<>();
    }

    @Test
    public void testParseToBookPositive() throws ServiceException {
        strings.add("11111");
        strings.add("author");
        strings.add("title");
        strings.add("2020");
        strings.add("20");
        CustomBook actual = parser.parseToBook(strings);
        CustomBook expected = new CustomBook(11111, "author", "title", 2020, new BigDecimal("20"));
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testParseToBookNegative() throws ServiceException {
        strings.add("11111");
        strings.add("author");
        strings.add("title");
        strings.add("2020");
        strings.add("20");
        strings.add(" ");
        parser.parseToBook(strings);
        fail("No exception was thrown!");
    }

    @Test
    public void testParseToIntPositive() throws ServiceException {
        int actual = parser.parseToInt("123");
        int expected = 123;
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testParseToIntNegative() throws ServiceException {
        int actual = parser.parseToInt("123O");
        fail("No exception was thrown!");
    }
}