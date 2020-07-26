package com.shilovich.day8.controller.invoker;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.controller.provider.ActionProvider;
import com.shilovich.day8.exception.ControllerException;

import java.util.HashMap;
import java.util.Map;

public class InvokeController<A, T, K> {
    private static InvokeController instance;

    private InvokeController() {
    }

    public static <A, T, K> InvokeController<A, T, K> getInstance() {
        if (instance == null) {
            instance = new InvokeController<A, T, K>();
        }
        return instance;
    }

    public Map<A, T> processRequest(String command, Map<A, K> params) {
        Map<A, T> result = new HashMap<>();
        try {
            ActionProvider provider = new ActionProvider();
            ActionCommand actionCommand = provider.defineCommand(command);
            result = actionCommand.execute(params);
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}