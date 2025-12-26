package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.core.adapter.clipboard.Clipboard;
import org.example.astero_demo.core.adapter.state.ModelStateAdapter;
import org.example.astero_demo.core.context.ops.OpsStateHolder;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.entity.ShapeFactory;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.context.state.ModelStateHolder;
import org.example.astero_demo.core.context.state.MutableModelState;
import org.example.astero_demo.model.metadata.dto.ShapeParams;
import org.example.astero_demo.realization.context.ops.runtime.Configuration;
import org.example.astero_demo.realization.context.ops.runtime.ConfigurationInstance;
import org.example.astero_demo.realization.context.ops.runtime.MutableConfiguration;

/**
 * DI config for model classes
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class ContextModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ModelState.class).to(ModelStateHolder.class);
        bind(MutableModelState.class).to(ModelStateHolder.class);
        bind(ModelStateHolder.class).toInstance(ModelStateHolder.INSTANCE);
        bind(ShapeFactory.class).toInstance(ShapeFactory.INSTANCE);

        bind(new TypeLiteral<Clipboard<Shape, ShapeParams>>() {}).toInstance(OpsStateHolder.INSTANCE);

        bind(Configuration.class).to(MutableConfiguration.class).in(Scopes.SINGLETON);
        bind(MutableConfiguration.class).to(ConfigurationInstance.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public ModelStateAdapter provideModelAdapter(final ShapeFactory factory, final MutableModelState holder) {
        return new ModelStateAdapter(factory, holder);
    }
}
