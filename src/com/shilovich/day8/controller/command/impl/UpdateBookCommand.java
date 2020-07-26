package com.shilovich.day8.controller.command.impl;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateBookCommand implements ActionCommand<String, Boolean, List<String>> {
    private static final String BOOK_REQ = "Book";
    private static final String IS_UPDATE_RESP = "isUpdate";

    private final CustomBookServiceImpl service;

    public UpdateBookCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, Boolean> execute(Map<String, List<String>> params) {
        List<String> book = params.get(BOOK_REQ);
        Map<String, Boolean> result = new HashMap<>();
        if (book.isEmpty()) {
            return result;
        }
        boolean isUpdate = false;
        try {
            service.update(book);
            isUpdate = true;
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        result.put(IS_UPDATE_RESP, isUpdate);
        return result;
    }
}