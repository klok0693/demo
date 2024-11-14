package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

public class ViewController extends AbstractController {
    @Setter
    private RootAdapter adapter;
    private final ShapeValidator validator;

    public ViewController(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ShapeValidator validator) {
        super(commandFactory, commandProcessor);
        this.validator = validator;
    }

    @Override
    public void update() {
        adapter.update();
    }

    @Override
    protected boolean isValid(final LogicEvent event) {
        if (event instanceof final ModifyShapeEvent ev) {
            return validator.isValid(ev.getParam(), ev.getNewValue());
        }
        return true;
    }
}
