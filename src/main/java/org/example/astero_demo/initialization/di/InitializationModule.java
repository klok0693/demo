package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeFactory;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.initialization.CustomControllerFactory;
import org.example.astero_demo.initialization.NodeBuilderFactory;

public class InitializationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomControllerFactory.class).in(Scopes.SINGLETON);
        bind(NodeBuilderFactory.class).in(Scopes.SINGLETON);
    }
}
