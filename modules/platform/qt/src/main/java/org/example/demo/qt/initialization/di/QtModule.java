package org.example.demo.qt.initialization.di;

import com.google.inject.AbstractModule;

public class QtModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QtInitializationModule());
        install(new QtAsynchModule());
        install(new QtUIElementModule());
        install(new QtViewModule());
    }
}
