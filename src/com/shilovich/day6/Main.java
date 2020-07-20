package com.shilovich.day6;

import com.shilovich.day6.controller.invoker.InvokeController;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InvokeController controller = InvokeController.getInstance();
        Map map = controller.processRequest("sort_by_tag_command", new HashMap());
        System.out.println(map.get("Books"));
    }
}
