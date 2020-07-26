package com.shilovich.day8.controller.command;

import java.util.Map;

public interface ActionCommand<A, T, K> {
    Map<A, T> execute(Map<A, K> params);
}
