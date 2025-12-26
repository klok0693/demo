package org.example.demo;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.example.demo.realization.initialization.launch.AppInitializer;
import org.example.demo.swing.realization.initialization.di.SwingModule;

import javax.swing.*;
import java.util.List;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class SwingAppInitializer extends AppInitializer<JFrame> {

    @Override
    protected List<Module> getModules() {
        final var modules = super.getModules();
        modules.add(new SwingModule());
        return modules;
    }

    @Override
    protected JFrame launchGUI(final Injector injector) {
        return buildApplication(injector).launchGUI();
    }

    protected SwingHelloApplication buildApplication(final Injector injector) {
        return injector.getInstance(SwingHelloApplication.class);
    }
}
