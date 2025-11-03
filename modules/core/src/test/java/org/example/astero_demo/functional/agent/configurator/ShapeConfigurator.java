package org.example.astero_demo.functional.agent.configurator;

import io.cucumber.java.en.Given;
import org.example.astero_demo.core.logic.LogicShapeProcessor;
import org.example.astero_demo.functional.TestComponentHolder;

public class ShapeConfigurator extends Configurator {
    private final LogicShapeProcessor logicShapeProcessor;

    public ShapeConfigurator(
            final TestComponentHolder componentHolder,
            final LogicShapeProcessor logicShapeProcessor) {
        super(componentHolder);
        this.logicShapeProcessor = logicShapeProcessor;
    }

    @Given("Shape with id: {int}")
    public void setupNewShape(final int ShapeId) {

        logicShapeProcessor.createShape();
    }
}
