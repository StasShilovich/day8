package com.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.model.entity.CustomBook;
import com.shilovich.day6.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingByAuthorCommand implements ActionCommand<String, List<CustomBook>, String> {
    private static final String SORT_BOOKS_RESP = "Books";
    private final CustomBookServiceImpl service;

    public SortingByAuthorCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, List<CustomBook>> execute(Map<String, String> params) {
        List<CustomBook> customBooks;
        customBooks = service.sortBookByAuthor();
        Map<String, List<CustomBook>> map = new HashMap<>();
        map.put(SORT_BOOKS_RESP, customBooks);
        return map;
    }
}
