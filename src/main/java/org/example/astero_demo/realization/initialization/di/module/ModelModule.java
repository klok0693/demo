package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.entity.ShapeFactory;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.model.state.ModelStateHolder;

public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ModelState.class).to(ModelStateHolder.class);
        bind(ModelStateHolder.class).toInstance(ModelStateHolder.INSTANCE);
        bind(ShapeFactory.class).toInstance(ShapeFactory.INSTANCE);
        //bind(ModelAdapter.class).toProvider(ModelAdapterProvider.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public ModelAdapter provideModelAdapter(final ShapeFactory factory, final ModelState holder) {
        return new ModelAdapter(factory, holder);
    }
}
