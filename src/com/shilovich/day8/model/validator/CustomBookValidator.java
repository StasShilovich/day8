package com.shilovich.day8.model.validator;

import com.shilovich.day8.model.entity.CustomBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomBookValidator {
    private static final int AUTHOR_NAME_LENGTH = 20;
    private static final int TITLE_NAME_LENGTH = 50;
    private static final int TOP_PRICE = 250;
    private static final int ZERO = 0;
    private static final String NOT_NUMBERS_REGEX = "^[a-zA-Z]*$";

    public boolean validateCustomBook(CustomBook book) {
        boolean result = false;
        if (book == null) {
            return false;
        }
        if (validateId(book.getId()) &&
                validateAuthor(book.getAuthor()) &&
                validateTitle(book.getTitle()) &&
                validateYear(book.getYear()) &&
                validatePrice(book.getPrice())) {
            result = true;
        }
        return result;
    }

    public boolean validateId(long id) {
        return id > ZERO;
    }

    public boolean validateParameter(String parameter) {
        Pattern pattern = Pattern.compile(NOT_NUMBERS_REGEX);
        Matcher matcher = pattern.matcher(parameter.trim());
        return matcher.matches();
    }

    private boolean validateAuthor(String author) {
        return author.length() <= AUTHOR_NAME_LENGTH && !author.isEmpty();
    }

    private boolean validateTitle(String title) {
        return title.length() <= TITLE_NAME_LENGTH && !title.isEmpty();
    }

    private boolean validateYear(int year) {
        return year <= LocalDate.now().getYear() && year > ZERO;
    }

    private boolean validatePrice(BigDecimal price) {
        return price.intValue() <= TOP_PRICE && price.intValue() > ZERO;
    }
}