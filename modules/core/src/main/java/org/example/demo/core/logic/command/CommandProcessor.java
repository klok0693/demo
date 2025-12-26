package org.example.demo.core.logic.command;

import org.example.demo.core.context.ops.execution.CommandQueue;

import java.util.Optional;

/**
 * Singleton enum class representing that processes commands and allows undoing the last one
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CommandProcessor {

    private final CommandQueue commandQueue;

    public CommandProcessor(final CommandQueue commandsQueue) {
        this.commandQueue = commandsQueue;
    }

    public void processCommand(final Command command) {
        command.doCommand();
        commandQueue.add(command);
    }

    public void undoLastCommand() {
        Optional.ofNullable(commandQueue.pollLast()).ifPresent(Command::undoCommand);
    }
}
