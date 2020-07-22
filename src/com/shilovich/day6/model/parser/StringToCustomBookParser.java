package com.shilovich.day6.model.parser;

import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.entity.CustomBook;

import java.math.BigDecimal;
import java.util.List;

public class StringToCustomBookParser {
    private static final int FIELDS_COUNT = 6;

    public CustomBook parseToBook(List<String> fields) throws ServiceException {
        CustomBook book = new CustomBook();
        if (fields.size() != FIELDS_COUNT) {
            throw new ServiceException("Invalid size of incoming list!");
        }
        int count = 0;
        Long tag = Long.parseLong(fields.get(count++));
        String author = fields.get(count++);
        String title = fields.get(count++);
        Integer year = parseToInt(fields.get(count++));
        BigDecimal price;
        try {
            price = new BigDecimal(fields.get(count++));
        } catch (NumberFormatException e) {
            throw new ServiceException("Invalid price!");
        }
        Boolean deleted = Boolean.getBoolean(fields.get(count));
        if (!price.equals(new BigDecimal(0))) {
            book = new CustomBook(tag, author, title, year, price, deleted);
        }
        return book;
    }

    public int parseToInt(String tag) throws ServiceException {
        int result;
        try {
            result = Integer.parseInt(tag);
        } catch (NumberFormatException e) {
            throw new ServiceException("Invalid tag!");
        }
        return result;
    }
}
