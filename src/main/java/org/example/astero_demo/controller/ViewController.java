package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ParamEvent;

import java.util.Arrays;

import static java.lang.Double.parseDouble;

public class ViewController extends AbstractController {
    protected RootAdapter adapter;
    private final ShapeValidator validator;

    public ViewController(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final RootAdapter adapter,
            final ShapeValidator validator) {
        super(commandFactory, commandProcessor);
        this.adapter = adapter;
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
        if (event instanceof final ParamEvent ev) {
            return Arrays.stream(ev.getParamInfos())
                    .allMatch(info -> validator.isValid(info.getParam(), info.getNewValue()));
        }
        return true;
    }
}
