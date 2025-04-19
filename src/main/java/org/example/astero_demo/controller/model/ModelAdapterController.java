package org.example.astero_demo.controller.model;

import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.controller.AbstractController;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;

/**
 * This controller is responsible for handling logic events and updating the model through a ModelController.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ModelAdapterController extends AbstractController implements ModelController {
    private final ModelAdapter modelAdapter;

    public ModelAdapterController(final ModelAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    @Override
    public String getShapeParam(final int id, final ShapeParam param) {
        return modelAdapter.getShapeParam(id, param);
    }

    @Override
    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        modelAdapter.modifyShapeParam(id, param, newValue);
    }

    @Override
    public int saveShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {
        return modelAdapter.saveShape(priority, x, y, width, height, color, type);
    }

    @Override
    public int saveShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {
        return modelAdapter.saveShape(id, priority, x, y, width, height, color, type);
    }

    @Override
    public Shape removeShape(final int id) {
        return modelAdapter.removeShape(id);
    }
}
