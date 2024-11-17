package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;

public class ModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StateHolder.class).toInstance(StateHolder.INSTANCE);
        bind(ShapeFactory.class).toInstance(ShapeFactory.INSTANCE);
        bind(ModelAdapter.class).in(Scopes.SINGLETON);
    }
}
