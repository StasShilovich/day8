package com.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.exception.ServiceException;
import com.shilovich.day6.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveBookCommand implements ActionCommand<String, Boolean, List<String>> {
    private static final String BOOKS_REQ = "Books";
    private static final String IS_REMOVED_RESP = "isRemoved";

    private final CustomBookServiceImpl service;

    public RemoveBookCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, Boolean> execute(Map<String, List<String>> parameters) {
        List<String> book = parameters.get(BOOKS_REQ);
        boolean isRemoved = false;
        try {
            service.removeBook(book);
            isRemoved = true;
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put(IS_REMOVED_RESP, isRemoved);
        return result;
    }
}
