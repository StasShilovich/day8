package com.shilovich.day8.controller.command.impl;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.exception.ServiceException;
import com.shilovich.day8.model.entity.CustomBook;
import com.shilovich.day8.model.service.impl.CustomBookServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByParameterCommand implements ActionCommand<String, List<CustomBook>, String> {
    private static final String SORT_BOOKS_PARAM_REQ = "Parameter";
    private static final String SORT_BOOKS_RESP = "Books";
    private final CustomBookServiceImpl service;

    public SortByParameterCommand() {
        this.service = CustomBookServiceImpl.getInstance();
    }

    @Override
    public Map<String, List<CustomBook>> execute(Map<String, String> params) {
        String parameter = params.get(SORT_BOOKS_PARAM_REQ);
        Map<String, List<CustomBook>> result = new HashMap<>();
        if (parameter.isEmpty()) {
            return result;
        }
        List<CustomBook> customBooks = new ArrayList<>();
        try {
            customBooks = service.sortBookByParameter(parameter);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        result.put(SORT_BOOKS_RESP, customBooks);
        return result;
    }
}