package com.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddBookCommand implements ActionCommand<String, Boolean, List<String>> {
    private static final String BOOK_REQ = "Book";
    private static final String IS_ADD_RESP = "isAdd";

    private final CustomBookServiceImpl service;

    public AddBookCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, Boolean> execute(Map<String, List<String>> parameters) {
        List<String> book = parameters.get(BOOK_REQ);
        boolean isAdd = false;
        try {
            service.addBook(book);
            isAdd = true;
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put(IS_ADD_RESP, isAdd);
        return result;
    }
}
