package org.example.astero_demo.initialization;

import javafx.util.Callback;
import lombok.Getter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.ToolBarAdapter;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.adapter.ui.state.UIStateHolder;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

public class CustomControllerFactory implements Callback<Class<?>, Object> {
    private final StateHolder holder = StateHolder.INSTANCE;
    private final ShapeFactory factory = ShapeFactory.INSTANCE;

    private final ModelAdapter modelAdapter = new ModelAdapter(factory, holder);
    private final CommandFactory commandFactory = new CommandFactory();
    private final CommandProcessor commandProcessor = CommandProcessor.INSTANCE;

    private final ViewController viewController = new ViewController(commandFactory, commandProcessor);
    private final ModelController modelController = new ModelController(commandFactory, commandProcessor);

    private MutableUIState uiState = UIStateHolder.INSTANCE;
    private final CanvasAdapter canvasAdapter = new CanvasAdapter(viewController, holder, uiState);
    private final ToolBarAdapter toolBarAdapter = new ToolBarAdapter(viewController, uiState);
    private final RootAdapter rootAdapter = new RootAdapter(viewController, uiState);

    public CustomControllerFactory() {
        this.commandFactory.setViewController(viewController);
        this.commandFactory.setModelController(modelController);

        this.viewController.setAdapter(rootAdapter);
        this.modelController.setModelAdapter(modelAdapter);
    }

    @Override
    public Object call(Class<?> aClass) {
        if (aClass.isAssignableFrom(CanvasAdapter.class)) {
            return canvasAdapter;
        }
        if (aClass.isAssignableFrom(ToolBarAdapter.class)) {
            return toolBarAdapter;
        }
        if (aClass.isAssignableFrom(RootAdapter.class)) {
            return rootAdapter;
        }
        return null;
    }
}
