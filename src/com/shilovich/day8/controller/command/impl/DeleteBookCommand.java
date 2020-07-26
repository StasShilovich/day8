package com.shilovich.day8.controller.command.impl;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class DeleteBookCommand implements ActionCommand<String, Boolean, Long> {
    private static final String BOOKS_REQ = "Id";
    private static final String IS_REMOVED_RESP = "isRemoved";
    private static final Long ZERO = 0L;

    private final CustomBookServiceImpl service;

    public DeleteBookCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, Boolean> execute(Map<String, Long> parameters) {
        Long id = parameters.get(BOOKS_REQ);
        Map<String, Boolean> result = new HashMap<>();
        if (id.equals(ZERO)) {
            return result;
        }
        boolean isRemoved = false;
        try {
            service.delete(id);
            isRemoved = true;
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        result.put(IS_REMOVED_RESP, isRemoved);
        return result;
    }
}