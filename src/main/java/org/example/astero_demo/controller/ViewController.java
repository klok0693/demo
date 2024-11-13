package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.UIEvent;

public class ViewController {
    @Setter
    private RootAdapter adapter;
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    public ViewController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        this.commandFactory = commandFactory;
        this.commandProcessor = commandProcessor;
    }

    public void process(final UIEvent e) {
        Command command = null;
        if (e instanceof CreateNewShapeEvent) {
            final CreateNewShapeEvent ev = (CreateNewShapeEvent) e;
            command = commandFactory.createNewShapeCommand(ev.getPriority(), ev.getX(), ev.getY(), ev.getType());
        }

        if (command != null) {
            commandProcessor.processCommand(command);
        }
    }
}
