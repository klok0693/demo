package org.example.astero_demo.realization.initialization.launch;

import com.google.inject.*;
import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.initialization.di.module.CoreModule;

import java.util.LinkedList;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
@Slf4j
public abstract class AppInitializer<E> implements Initializer<E> {

    @Override
    public final E initialize(final String... args) {
        log.debug(INITIALIZATION_MARKER, "Initializing DI container");
        final Injector injector = Guice.createInjector(getModules());

        log.debug(INITIALIZATION_MARKER, "Setting up configuration");
        AppConfiguration.INSTANCE.setUp();

        log.debug(INITIALIZATION_MARKER, "Launching GUI");
        return launchGUI(injector);
    }

    protected List<Module> getModules() {
        final List<Module> list = new LinkedList<>();
        list.add(new CoreModule());
        return list;
    }

    protected abstract E launchGUI(Injector injector);
}
