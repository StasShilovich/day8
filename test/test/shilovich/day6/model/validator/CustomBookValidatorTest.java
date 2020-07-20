package test.shilovich.day6.model.validator;

import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.validator.CustomBookValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomBookValidatorTest {
    CustomBookValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new CustomBookValidator();
    }

    @DataProvider(name = "bookPositive")
    public Object[][] createDataPositive() {
        return new Object[][]{{new CustomBook(41554, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"))},
                {new CustomBook(96942, "Albert Camus", "The Plague",
                        1947, new BigDecimal("12.49"))}};
    }

    @Test(dataProvider = "bookPositive")
    public void testCustomBookValidationPositive(CustomBook book) {
        boolean condition = validator.validateCustomBook(book);
        assertTrue(condition);
    }

    @DataProvider(name = "bookNegative")
    public Object[][] createDataNegative() {
        return new Object[][]{{new CustomBook(415524, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"))},
                {new CustomBook(96942, "Albert Camusaaaaaaaaa", "The Plague",
                        1947, new BigDecimal("12.49"))},
                {new CustomBook(59725, "Shashi Tharoor", "The Great Indian Novel",
                        2077, new BigDecimal("24.36"))},
                {new CustomBook(29318, "Paulo Coelho", "The Devil and Miss Prym",
                        2000, new BigDecimal("300"))},
                {new CustomBook(16898, "Patrick Süskin", "Perfume: The Story of a Murdereraaaaaaaaaaaaaaaaaaaaaaa",
                        1985, new BigDecimal("10.47"))}};
    }

    @Test(dataProvider = "bookNegative")
    public void testCustomBookValidationNegative(CustomBook book) {
        boolean condition = validator.validateCustomBook(book);
        assertFalse(condition);
    }
}