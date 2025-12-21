package org.example.astero_demo.swing.initialization.di;

import com.google.inject.AbstractModule;

/**
 * Container for all swing di modules
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SwingAsynchModule());
        install(new SwingUIElementModule());
        install(new SwingViewModule());
        install(new KeyboardModule());
    }
}
