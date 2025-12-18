package org.example.astero_demo.swing.initialization.di;

import com.google.inject.AbstractModule;

public class SwingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SwingInitializationModule());
        install(new SwingAsynchModule());
        install(new SwingUIElementModule());
        install(new SwingViewModule());
    }
}
