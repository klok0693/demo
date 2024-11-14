package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ModifyShapeEvent;

import static java.lang.Double.parseDouble;

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

    public void onCreateUpdate(final String newShapeX, final String newShapeY) {
        adapter.onCreateUpdate(parseDouble(newShapeX), parseDouble(newShapeY));
    }

    public void onModifyUpdate() {
        adapter.onModifyUpdate();
    }

    public void onRemoveUpdate() {
        adapter.onRemoveUpdate();
    }

    @Override
    protected boolean isValid(final LogicEvent event) {
        if (event instanceof final ModifyShapeEvent ev) {
            return validator.isValid(ev.getParam(), ev.getNewValue());
        }
        return true;
    }
}
