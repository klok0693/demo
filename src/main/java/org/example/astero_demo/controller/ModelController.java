package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.LogicEvent;

public class ModelController extends AbstractController {
    @Setter
    private ModelAdapter modelAdapter;

    public ModelController(CommandFactory commandFactory, CommandProcessor commandProcessor) {
        super(commandFactory, commandProcessor);
    }

    public String getShapeParam(final int id, final ShapeParam param) {
        return modelAdapter.getShapeParam(id, param);
    }

    public void modifyShapeParam(final int id, final ShapeParam param, final String newValue) {
        modelAdapter.modifyShapeParam(id, param, newValue);
    }

    public int saveShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {
        return modelAdapter.saveShape(priority, x, y, width, height, type);
    }

    public int saveShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {
        return modelAdapter.saveShape(id, priority, x, y, width, height, type);
    }

    public Shape removeShape(final int id) {
        return modelAdapter.removeShape(id);
    }

    @Override
    protected boolean isValid(LogicEvent event) {
        return true;
    }
}
