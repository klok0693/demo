package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.adapter.ui.canvas.CanvasAdapter;
import org.example.astero_demo.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.realization.async.AppExecutor;
import org.example.astero_demo.realization.async.FXExecutor;
import org.example.astero_demo.realization.async.wrappers.adapter.CanvasAdapterWrapper;
import org.example.astero_demo.realization.async.wrappers.adapter.LayersPanelAdapterWrapper;

public class AsyncModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppExecutor.class).to(FXExecutor.class);
        bind(FXExecutor.class).toInstance(FXExecutor.INSTANCE);

        bind(CanvasAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(CanvasAdapter.class).to(CanvasAdapterWrapper.class);

        bind(LayersPanelAdapterWrapper.class).in(Scopes.SINGLETON);
        bind(LayersAdapter.class).to(LayersPanelAdapterWrapper.class);
    }
}
