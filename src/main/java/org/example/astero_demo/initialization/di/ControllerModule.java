package org.example.astero_demo.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ShapeValidator;
import org.example.astero_demo.controller.ViewController;
import org.example.astero_demo.initialization.CustomControllerFactory;
import org.example.astero_demo.initialization.di.provider.controller.ModelControllerProvider;
import org.example.astero_demo.initialization.di.provider.controller.ViewControllerProvider;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ShapeValidator.class).toInstance(ShapeValidator.INSTANCE);
        bind(ViewController.class).toProvider(ViewControllerProvider.class).in(Scopes.SINGLETON);
        bind(ModelController.class).toProvider(ModelControllerProvider.class).in(Scopes.SINGLETON);
    }
}
