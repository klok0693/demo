package org.example.demo.qt.initialization.di;

import com.google.inject.AbstractModule;

/**
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class QtModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new QtAsynchModule());
        install(new QtUIElementModule());
        install(new QtViewModule());
    }
}
