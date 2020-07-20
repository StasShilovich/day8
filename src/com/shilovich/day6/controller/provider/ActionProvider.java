package com.shilovich.day6.controller.provider;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.type.CommandType;
import com.shilovich.day6.controller.command.impl.EmptyCommand;
import com.shilovich.day6.exception.ControllerException;

public class ActionProvider {
    public ActionCommand defineCommand(String command) throws ControllerException {
        if (command.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(command.toUpperCase());
            return currentEnum.getCommand();
        } catch (IllegalArgumentException e) {
            throw new ControllerException("Command name is invalid: " + command);
        }
    }
}
