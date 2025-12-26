package org.example.astero_demo.swing.realization.initialization.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.example.astero_demo.core.port.keyboard.RootShortcutHandler;
import org.example.astero_demo.swing.port.keyboard.SwingRootShortcutHandler;

/**
 * Config, initialize and show keyboard classes
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class KeyboardModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(RootShortcutHandler.class).to(SwingRootShortcutHandler.class).in(Scopes.SINGLETON);
    }
}
