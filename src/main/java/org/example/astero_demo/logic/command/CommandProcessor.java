package org.example.astero_demo.logic.command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Singleton enum class representing that processes commands and allows undoing the last one
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
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
