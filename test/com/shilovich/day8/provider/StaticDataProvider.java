package com.shilovich.day8.provider;

import com.shilovich.day8.model.entity.CustomBook;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StaticDataProvider {
    private static final CustomBook book1;
    private static final CustomBook book2;
    private static final CustomBook book3;
    private static final CustomBook book4;
    private static final CustomBook book5;

    static {
        book1 = new CustomBook(1L, "Ernest Hemingway", "For Whom the Bell Tolls", 1940,
                new BigDecimal("12.99"), false);
        book2 = new CustomBook(2L, "Albert Camus", "The Plague", 1947,
                new BigDecimal("12.49"), false);
        book3 = new CustomBook(3L, "Shashi Tharoor", "The Great Indian Novel", 1989,
                new BigDecimal("24.36"), false);
        book4 = new CustomBook(4L, "Paulo Coelho", "The Devil and Miss Prym", 2000,
                new BigDecimal("9.99"), false);
        book5 = new CustomBook(5L, "Patrick Suskin", "Perfume: The Story of a Murderer", 1985,
                new BigDecimal("10.47"), false);
    }

    @DataProvider(name = "Books")
    public static Object[] createBooks() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        return new Object[]{books};
    }

    @DataProvider(name = "SortByAuthor")
    public static Object[] createBooksByAuthor() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book2);
        books.add(book1);
        books.add(book5);
        books.add(book4);
        books.add(book3);
        return new Object[]{books};
    }

    @DataProvider(name = "SortByTitle")
    public static Object[] createBooksByTitle() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book5);
        books.add(book3);
        books.add(book4);
        return new Object[]{books};
    }

    @DataProvider(name = "SortByYear")
    public static Object[] createBooksByYear() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book5);
        books.add(book3);
        books.add(book4);
        return new Object[]{books};
    }

    @DataProvider(name = "SortByPrice")
    public static Object[] createBooksByPrice() {
        List<CustomBook> books = new ArrayList<>();
        books.add(book4);
        books.add(book5);
        books.add(book2);
        books.add(book1);
        books.add(book3);
        return new Object[]{books};
    }

    @DataProvider(name = "FindById")
    public static Object[][] getBooksAndId() {
        return new Object[][]{{1L, book1}, {2L, book2}, {3L, book3}, {4L, book4}, {5L, book5}};
    }
}