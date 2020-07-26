package com.shilovich.day8.model.parser;

import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;

import java.math.BigDecimal;
import java.util.List;

public class StringToCustomBookParser {
    private static final int ZERO = 0;
    private static final int FIELDS_COUNT = 6;
    private static final int ID_INDEX = 0;
    private static final int AUTHOR_INDEX = 1;
    private static final int TITLE_INDEX = 2;
    private static final int YEAR_INDEX = 3;
    private static final int PRICE_INDEX = 4;
    private static final int DELETED_INDEX = 5;

    public CustomBook parseToBook(List<String> fields) throws ServiceException {
        if (fields.size() != FIELDS_COUNT) {
            throw new ServiceException("Invalid size of incoming list!");
        }
        CustomBook book = new CustomBook();
        try {
            String idStr = fields.get(ID_INDEX).trim();
            long id = 0L;
            if (!idStr.isEmpty()) {
                id = Long.parseLong(idStr);
            }
            String author = fields.get(AUTHOR_INDEX).trim();
            String title = fields.get(TITLE_INDEX).trim();
            String yearStr = fields.get(YEAR_INDEX).trim();
            int year = 0;
            if (!yearStr.isEmpty()) {
                year = Integer.parseInt(yearStr);
            }
            String priceStr = fields.get(PRICE_INDEX).trim();
            BigDecimal price = new BigDecimal(ZERO);
            if (!priceStr.isEmpty()) {
                price = new BigDecimal(priceStr);
            }
            String deletedStr = fields.get(DELETED_INDEX).trim();
            boolean deleted = false;
            if (!deletedStr.isEmpty()) {
                deleted = Boolean.parseBoolean(deletedStr);
            }
            book = new CustomBook(id, author, title, year, price, deleted);
        } catch (NumberFormatException e) {
            throw new ServiceException("Parse list to book fail!", e);
        }
        return book;
    }
}