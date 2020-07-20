package com.shilovich.day6.controller.command.impl;

import com.shilovich.day6.controller.command.ActionCommand;

import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements ActionCommand<String, String, String> {
    @Override
    public Map<String, String> execute(Map<String, String> parameters) {
        System.out.println("Empty command!");
        return new HashMap<>();
    }
}
