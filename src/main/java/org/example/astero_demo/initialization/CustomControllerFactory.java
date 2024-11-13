package org.example.astero_demo.initialization;

import javafx.util.Callback;
import lombok.Getter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.ToolBarAdapter;
import org.example.astero_demo.controller.ViewController;

@Getter
public class CustomControllerFactory implements Callback<Class<?>, Object> {
    private final StateHolder holder = StateHolder.INSTANCE;
    private final ShapeFactory factory = ShapeFactory.INSTANCE;
    private final ModelAdapter modelAdapter = new ModelAdapter(factory, holder);

    private final ViewController viewController = new ViewController(modelAdapter);

    private final CanvasAdapter canvasAdapter = new CanvasAdapter(viewController, holder);
    private final ToolBarAdapter toolBarAdapter = new ToolBarAdapter(viewController);
    private final RootAdapter rootAdapter = new RootAdapter(viewController);


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
