package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.astero_demo.core.adapter.state.ModelStateAdapter;
import org.example.astero_demo.model.entity.ShapeFactory;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.context.state.ModelStateHolder;
import org.example.astero_demo.core.context.state.MutableModelState;

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
    public ModelStateAdapter provideModelAdapter(final ShapeFactory factory, final MutableModelState holder) {
        return new ModelStateAdapter(factory, holder);
    }
}
