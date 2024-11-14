package org.example.astero_demo.controller;

import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;
import org.example.astero_demo.logic.event.ui.RemoveShapeEvent;
import org.example.astero_demo.logic.event.ui.LogicEvent;

import static java.lang.String.valueOf;

public abstract class AbstractController {
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    public AbstractController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }

    public void process(final LogicEvent e) {
        if (!isValid(e)) {
            // TODO: log
            return;
        }

        Command command = null;
        if (e instanceof final CreateNewShapeEvent ev) {
            command = commandFactory.createNewShapeCommand(
                    valueOf(ev.getPriority()), valueOf(ev.getX()), valueOf(ev.getY()), ev.getType());
        }
        else if (e instanceof final ModifyShapeEvent ev) {
            command = commandFactory.createModifyShapeCommand(
                    ev.getShapeId(), ev.getParam(), valueOf(ev.getNewValue()));
        }
        else if (e instanceof final RemoveShapeEvent ev) {
            command = commandFactory.createRemoveShapeCommand(ev.getShapeId());
        }

        if (command != null) {
            commandProcessor.processCommand(command);
        }
    }

    protected abstract boolean isValid(final LogicEvent event);
}
