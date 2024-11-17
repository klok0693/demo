package org.example.astero_demo.initialization;

import com.google.inject.Inject;
import javafx.util.Callback;
import org.example.astero_demo.adapter.keyboard.RootShortcutHandler;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.*;
import org.example.astero_demo.adapter.ui.state.MutableUIState;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

public class CustomControllerFactory implements Callback<Class<?>, Object> {

    private final LayersAdapter layersAdapter;
    private final PropertyAdapter propertyAdapter;
    private final CanvasAdapter canvasAdapter;
    private final ToolBarAdapter toolBarAdapter;
    private final RootAdapter rootAdapter;

    @Inject
    public CustomControllerFactory(
            final LayersAdapter layersAdapter,
            final PropertyAdapter propertyAdapter,
            final CanvasAdapter canvasAdapter,
            final ToolBarAdapter toolBarAdapter,
            final RootAdapter rootAdapter) {
        this.layersAdapter = layersAdapter;
        this.propertyAdapter = propertyAdapter;
        this.canvasAdapter = canvasAdapter;
        this.toolBarAdapter = toolBarAdapter;
        this.rootAdapter = rootAdapter;
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
        if (aClass.isAssignableFrom(PropertyAdapter.class)) {
            return propertyAdapter;
        }
        if (aClass.isAssignableFrom(LayersAdapter.class)) {
            return layersAdapter;
        }
        return null;
    }
}
