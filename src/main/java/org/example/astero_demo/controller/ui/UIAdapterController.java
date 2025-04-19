package org.example.astero_demo.controller.ui;

import org.example.astero_demo.controller.AbstractController;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;
import org.example.astero_demo.logic.event.ui.ParamEvent;

import java.util.Arrays;

/**
 * This controller is responsible for handling logic events and updating the UI through a ControllerAdapter.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class UIAdapterController extends AbstractController implements UIController {
    private final ControllerAdapter adapter;

    public UIAdapterController(final ControllerAdapter adapter) {
        this.adapter = adapter;
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
}
