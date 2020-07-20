package com.shilovich.day6.model.entity;

import java.util.List;

public class CustomBookStorage {
    private static final int STORAGE_CAPACITY = 100;
    private static CustomBookStorage instance;

    private List<CustomBook> books;

    private CustomBookStorage() {
    }

    public static CustomBookStorage getInstance() {
        if (instance == null) {
            instance = new CustomBookStorage();
        }
        return instance;
    }

    public CustomBook getBook(int index) {
        return books.get(index);
    }

    public boolean setBook(CustomBook book) {
        boolean result = false;
        if (book != null && isEnoughSpace(1)) {
            this.books.add(book);
            result = true;
        }
        return result;
    }

    public boolean setBooks(List<CustomBook> books) {
        boolean result = false;
        if (!books.isEmpty() && isEnoughSpace(books.size())) {
            if (this.books == null) {
                this.books = books;
            } else {
                this.books.addAll(books);
            }
            result = true;
        }
        return result;
    }

    public boolean deleteBook(int index) {
        return books.remove(getBook(index));
    }

    public int size() {
        return books.size();
    }

    public boolean isEnoughSpace(int count) {
        if (this.books == null) {
            return STORAGE_CAPACITY - count >= 0;
        } else {
            return (STORAGE_CAPACITY - size()) >= count;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBookStorage{");
        sb.append("books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
