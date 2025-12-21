package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.ui.canvas.CanvasView;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersView;
import org.example.astero_demo.core.adapter.ui.property.PropertiesView;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.port.keyboard.RootShortcutHandler;
import org.example.astero_demo.core.port.keyboard.ShortcutHandler;
import org.example.astero_demo.core.port.ui.LayersPanelView;
import org.example.astero_demo.core.port.ui.PropertiesPanelView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;
import org.example.astero_demo.core.port.ui.canvas.ShapeCanvasView;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CanvasView.class).to(ShapeCanvasView.class).in(Scopes.SINGLETON);
        bind(ToolBarView.class).to(ToolBarPanelView.class).in(Scopes.SINGLETON);
        bind(PropertiesView.class).to(PropertiesPanelView.class).in(Scopes.SINGLETON);
        bind(LayersView.class).to(LayersPanelView.class).in(Scopes.SINGLETON);

        bind(ShortcutHandler.class).to(RootShortcutHandler.class).in(Scopes.SINGLETON);
    }
}
