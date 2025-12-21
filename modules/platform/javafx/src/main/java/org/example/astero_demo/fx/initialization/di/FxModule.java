package org.example.astero_demo.fx.initialization.di;

import com.google.inject.AbstractModule;

public class FxModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FxInitializationModule());
        install(new FxAsynchModule());
        install(new FxUIElementModule());
        install(new FxViewModule());
    }
}
