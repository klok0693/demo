package org.example.demo.functional.agent.configurator;

import io.cucumber.java.en.Given;
import org.example.demo.core.logic.LogicShapeProcessor;
import org.example.demo.functional.TestComponentHolder;
import org.example.demo.model.entity.ShapeType;
import org.example.demo.model.metadata.ShapeParam;
import org.example.demo.model.metadata.dto.ShapeParams;

public class ShapeConfigurator extends Configurator {
    //<editor-fold desc="test shape params">
    private static final ShapeParams params = new ShapeParams(50, 50, 100, 100, ShapeType.ELLIPSE);
    //</editor-fold>

    public ShapeConfigurator(final TestComponentHolder holder) {
        super(holder);
    }

    @Given("Shape with id: {int}")
    public void setupNewShape(final int shapeId) {
        params.setNewValue(ShapeParam.ID, String.valueOf(shapeId));
        getInstance(LogicShapeProcessor.class).createShape(params);
    }
}
