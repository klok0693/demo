package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.adapter.keyboard.OperationAdapter;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.adapter.ui.property.PropertiesAdapter;
import org.example.astero_demo.adapter.ui.toolbar.ToolBarAdapter;
import org.example.astero_demo.realization.async.AppExecutor;
import org.example.astero_demo.realization.async.FXExecutor;
import org.example.astero_demo.realization.async.wrappers.adapter.*;

/**
 * DI config for async wrappers
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class AsyncModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppExecutor.class).to(FXExecutor.class);
        bind(FXExecutor.class).toInstance(FXExecutor.INSTANCE);

        bind(CanvasAdapter.class).to(CanvasAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(LayersAdapter.class).to(LayersPanelAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(PropertiesAdapter.class).to(PropertiesAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(ToolBarAdapter.class).to(ToolBarAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(OperationAdapter.class).to(OperationAdapterWrapper.class).in(Scopes.SINGLETON);
    }
}
