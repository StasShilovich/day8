package com.shilovich.day6.controller.invoker;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.provider.ActionProvider;
import com.shilovich.day6.exception.ControllerException;

import java.util.HashMap;
import java.util.Map;

public class InvokeController<A, T, K> {
    private static InvokeController instance;

    private InvokeController() {
    }

    public static InvokeController getInstance() {
        if (instance == null) {
            return new InvokeController();
        }
        return instance;
    }

    public Map<A, T> processRequest(String command, Map<A, K> params) {
        Map<A, T> result = new HashMap();
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
