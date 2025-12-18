package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import org.example.astero_demo.core.adapter.ui.toolbar.ToolBarView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;

public class ViewModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ToolBarView.class).to(ToolBarPanelView.class);
    }
}
