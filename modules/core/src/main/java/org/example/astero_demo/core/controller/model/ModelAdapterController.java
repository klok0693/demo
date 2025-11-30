package org.example.astero_demo.core.controller.model;

import org.example.astero_demo.core.adapter.state.StateAdapter;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.core.controller.AbstractController;

/**
 * This controller is responsible for handling logic events and updating the model through a ModelController.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ModelAdapterController extends AbstractController implements ModelController {
    private final StateAdapter stateAdapter;

    public ModelAdapterController(final StateAdapter stateAdapter) {
        this.stateAdapter = stateAdapter;
    }

    @Override
    public String getShapeParam(final int id, final ShapeParam param) {
        return stateAdapter.getShapeParam(id, param);
    }

    @Override
    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        stateAdapter.modifyShapeParam(id, param, newValue);
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
        return stateAdapter.saveShape(priority, x, y, width, height, color, type);
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
        return stateAdapter.saveShape(id, priority, x, y, width, height, color, type);
    }

    @Override
    public Shape removeShape(final int id) {
        return stateAdapter.removeShape(id);
    }
}
