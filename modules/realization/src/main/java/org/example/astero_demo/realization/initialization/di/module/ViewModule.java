package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CanvasView.class).to(ShapeCanvasView.class);
        bind(ToolBarView.class).to(ToolBarPanelView.class);
        bind(PropertiesView.class).to(PropertiesPanelView.class);
    }
}
