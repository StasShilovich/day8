package com.shilovich.day8.controller.command.impl;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class FindBookByIdCommand implements ActionCommand<String, CustomBook, Long> {
    private static final String ID_REQ = "Id";
    private static final String BOOK_RESP = "Book";
    private static final int ZERO = 0;

    private final CustomBookServiceImpl service;

    public FindBookByIdCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, CustomBook> execute(Map<String, Long> params) {
        Map<String, CustomBook> result = new HashMap<>();
        Long id = params.get(ID_REQ);
        if (id == ZERO) {
            return result;
        }
        CustomBook book = new CustomBook();
        try {
            book = service.find(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        result.put(BOOK_RESP, book);
        return result;
    }
}