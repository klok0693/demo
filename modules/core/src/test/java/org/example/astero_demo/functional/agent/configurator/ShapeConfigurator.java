package org.example.astero_demo.functional.agent.configurator;

import io.cucumber.java.en.Given;
import org.example.astero_demo.core.context.state.ModelStateHolder;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.functional.TestComponentHolder;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

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
