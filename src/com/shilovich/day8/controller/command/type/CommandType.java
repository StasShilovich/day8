package com.shilovich.day8.controller.command.type;

import com.shilovich.day8.controller.command.ActionCommand;
import com.shilovich.day8.controller.command.impl.*;

public enum CommandType {
    ADD_BOOK_COMMAND(new AddBookCommand()),
    DELETE_BOOK_COMMAND(new DeleteBookCommand()),
    UPDATE_BOOK_COMMAND(new UpdateBookCommand()),
    FIND_ALL_BOOKS_COMMAND(new FindAllBooksCommand()),
    FIND_BOOK_BY_ID(new FindBookByIdCommand()),
    SORT_BY_PARAMETER(new SortByParameterCommand());


    private CommandType(ActionCommand command) {
        this.command = command;
    }

    private ActionCommand command;

    public ActionCommand getCommand() {
        return command;
    }
}
