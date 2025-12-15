package org.example.astero_demo.swing.initialization.di;

import com.google.inject.AbstractModule;

public class SwingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new InitializationModule());
        install(new FxAsynchModule());
        install(new UIElementModule());
        install(new UIViewModule());
    }
}
