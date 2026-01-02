package org.example.demo;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.example.demo.qt.initialization.di.QtModule;
import org.example.demo.realization.initialization.launch.AppInitializer;

import java.util.List;

/**
 * Fx applicatiob initializer
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */

public class QtAppInitializer extends AppInitializer {

    @Override
    protected List<Module> getModules() {
        final var modules = super.getModules();
        modules.add(new QtModule());
        return modules;
    }

    @Override
    protected Object launchGUI(final Injector injector) {
        //FxHelloApplication.setInjector(injector);
        //Application.launch(FxHelloApplication.class);
        return null;
    }
}
