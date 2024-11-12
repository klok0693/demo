package org.example.astero_demo.initialization;

import javafx.util.Callback;
import lombok.Getter;
import org.example.astero_demo.adapter.ui.CanvasAdapter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.adapter.ui.ToolBarAdapter;

@Getter
public class CustomControllerFactory implements Callback<Class<?>, Object> {
    private CanvasAdapter canvasAdapter = new CanvasAdapter();
    private ToolBarAdapter toolBarAdapter = new ToolBarAdapter();
    private RootAdapter rootAdapter = new RootAdapter();

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
