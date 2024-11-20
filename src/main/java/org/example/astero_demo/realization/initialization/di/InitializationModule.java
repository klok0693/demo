package org.example.astero_demo.realization.initialization.di;

import com.google.inject.*;
import javafx.fxml.LoadListener;
import org.example.astero_demo.realization.async.wrappers.adapter.CanvasAdapterWrapper;
import org.example.astero_demo.realization.async.wrappers.adapter.LayersPanelAdapterWrapper;
import org.example.astero_demo.realization.initialization.ui.CustomControllerFactory;
import org.example.astero_demo.realization.initialization.ui.NodeBuilderFactory;
import org.example.astero_demo.realization.initialization.ui.WrapperLoadListener;

public class InitializationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomControllerFactory.class).in(Scopes.SINGLETON);
        bind(NodeBuilderFactory.class).in(Scopes.SINGLETON);
        bind(LoadListener.class).to(WrapperLoadListener.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public WrapperLoadListener provideLoadListener(
            final CanvasAdapterWrapper canvasAdapterWrapper,
            final LayersPanelAdapterWrapper layersWrapper) {
        return new WrapperLoadListener(canvasAdapterWrapper, layersWrapper);
    }
}
