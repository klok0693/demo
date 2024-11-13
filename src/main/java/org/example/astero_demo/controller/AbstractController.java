package org.example.astero_demo.controller;

import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeLogicEvent;
import org.example.astero_demo.logic.event.ui.UILogicEvent;

public abstract class AbstractController {
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    public AbstractController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }

    public void process(final UILogicEvent e) {
        Command command = null;
        if (e instanceof CreateNewShapeLogicEvent) {
            final CreateNewShapeLogicEvent ev = (CreateNewShapeLogicEvent) e;
            command = commandFactory.createNewShapeCommand(ev.getPriority(), ev.getX(), ev.getY(), ev.getType());
        }

        if (command != null) {
            commandProcessor.processCommand(command);
        }
    }

    public abstract void update();
}
