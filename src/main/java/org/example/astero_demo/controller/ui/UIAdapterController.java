package org.example.astero_demo.controller.ui;

import org.example.astero_demo.controller.AbstractController;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ParamEvent;

import java.util.Arrays;

public class UIAdapterController extends AbstractController implements UIController {
    private final ControllerAdapter adapter;
    private final ShapeValidator validator;

    public UIAdapterController(
            final CommandFactory commandFactory,
            final CommandProcessor commandProcessor,
            final ControllerAdapter adapter,
            final ShapeValidator validator) {
        super(commandFactory, commandProcessor);
        this.adapter = adapter;
        this.validator = validator;
    }

    @Override
    public void onCreateUpdate(final int id) {
        adapter.onCreateUpdate(id);
    }

    @Override
    public void onModifyUpdate(final int id) {
        adapter.onModifyUpdate(id);
    }

    @Override
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
