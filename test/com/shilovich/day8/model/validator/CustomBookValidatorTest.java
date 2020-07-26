package com.shilovich.day8.model.validator;

import com.shilovich.day8.model.entity.CustomBook;
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
        return new Object[][]{{new CustomBook(1L, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"), true)},
                {new CustomBook(2L, "Albert Camus", "The Plague",
                        1947, new BigDecimal("12.49"), true)}};
    }

    @Test(dataProvider = "bookPositive")
    public void testCustomBookValidationPositive(CustomBook book) {
        boolean condition = validator.validateCustomBook(book);
        assertTrue(condition);
    }

    @DataProvider(name = "bookNegative")
    public Object[][] createDataNegative() {
        return new Object[][]{{new CustomBook(0L, "Ernest Hemingway", "For Whom the Bell Tolls",
                1940, new BigDecimal("12.99"), true)},
                {new CustomBook(1L, "Albert Camusaaaaaaaaa", "The Plague",
                        1947, new BigDecimal("12.49"), false)},
                {new CustomBook(2L, "Shashi Tharoor", "The Great Indian Novel",
                        2077, new BigDecimal("24.36"), true)},
                {new CustomBook(3L, "Paulo Coelho", "The Devil and Miss Prym",
                        2000, new BigDecimal("300"), false)},
                {new CustomBook(4L, "Patrick Süskin", "Perfume: The Story of a Murdereraaaaaaaaaaaaaaaaaaaaaaa",
                        1985, new BigDecimal("10.47"), true)}};
    }

    @Test(dataProvider = "bookNegative")
    public void testCustomBookValidationNegative(CustomBook book) {
        boolean condition = validator.validateCustomBook(book);
        assertFalse(condition);
    }

    @Test
    public void testParameterValidationPositive() {
        boolean condition = validator.validateParameter(" title ");
        assertTrue(condition);
    }

    @Test
    public void testParameterValidationNegative() {
        boolean condition = validator.validateParameter("title211");
        assertFalse(condition);
    }
}