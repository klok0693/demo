package org.example.astero_demo.logic.command;

import java.util.*;

public enum CommandProcessor {
    INSTANCE;

    private final Deque<Command> processedCommandsQueue = new LinkedList<>();

    public void processCommand(final Command command) {
        command.doCommand();
        processedCommandsQueue.add(command);
    }

    public void undoLastCommand() {
        Optional.ofNullable(processedCommandsQueue.pollLast()).ifPresent(Command::undoCommand);
    }
}
