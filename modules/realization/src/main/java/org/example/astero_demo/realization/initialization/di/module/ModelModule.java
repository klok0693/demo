package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.astero_demo.core.adapter.model.ModelAdapter;
import org.example.astero_demo.core.model.entity.ShapeFactory;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.model.state.ModelStateHolder;
import org.example.astero_demo.core.model.state.MutableModelState;

/**
 * DI config for model classes
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ModelState.class).to(ModelStateHolder.class);
        bind(MutableModelState.class).to(ModelStateHolder.class);
        bind(ModelStateHolder.class).toInstance(ModelStateHolder.INSTANCE);
        bind(ShapeFactory.class).toInstance(ShapeFactory.INSTANCE);
    }

    @Inject
    @Provides
    @Singleton
    public ModelAdapter provideModelAdapter(final ShapeFactory factory, final MutableModelState holder) {
        return new ModelAdapter(factory, holder);
    }
}
