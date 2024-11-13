package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.UIEvent;

public class ViewController extends AbstractController {
    @Setter
    private RootAdapter adapter;

    public ViewController(final CommandFactory commandFactory, final CommandProcessor commandProcessor) {
        super(commandFactory, commandProcessor);
    }

    @Override
    public void update() {
        adapter.update();
    }
}
