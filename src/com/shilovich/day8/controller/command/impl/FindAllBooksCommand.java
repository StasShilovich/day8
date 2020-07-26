package com.shilovich.day8.controller.command.impl;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllBooksCommand implements ActionCommand<String, List<CustomBook>, String> {
    private static final String BOOK_RESP = "Books";

    private final CustomBookServiceImpl service;

    public FindAllBooksCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, List<CustomBook>> execute(Map<String, String> parameters) {
        List<CustomBook> books = new ArrayList<>();
        try {
            books = service.findAll();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        Map<String, List<CustomBook>> result = new HashMap<>();
        result.put(BOOK_RESP, books);
        return result;
    }
}