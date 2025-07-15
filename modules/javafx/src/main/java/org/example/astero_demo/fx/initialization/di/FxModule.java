package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;

public class FxModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new InitializationModule());
        install(new FxAsynchModule());
        install(new UIElementModule());
        install(new UIViewModule());
    }
}
