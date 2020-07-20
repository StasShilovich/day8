package com.shilovich.day6.controller.command.type;

import com.shilovich.day6.controller.command.ActionCommand;
import com.shilovich.day6.controller.command.impl.*;

public enum CommandType {
    FIND_BY_TAG_COMMAND(new FindByTagCommand()),
    SORT_BY_TAG_COMMAND(new SortingByTagCommand()),
    SORT_BY_AUTHOR_COMMAND(new SortingByAuthorCommand()),
    SORT_BY_YEAR_COMMAND(new SortingByYearCommand()),
    SORT_BY_PRICE_COMMAND(new SortingByPriceCommand()),
    ADD_BOOK_COMMAND(new AddBookCommand()),
    REMOVE_BOOK_COMMAND(new RemoveBookCommand());

    private CommandType(ActionCommand command) {
        this.command = command;
    }

    private ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
