package org.example.demo;

import com.google.inject.Injector;
import com.google.inject.Module;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.demo.fx.initialization.di.FxModule;
import org.example.demo.realization.initialization.launch.AppInitializer;

import java.util.List;

/**
 * Fx applicatiob initializer
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */

public class FxAppInitializer extends AppInitializer<Stage> {

    @Override
    protected List<Module> getModules() {
        final var modules = super.getModules();
        modules.add(new FxModule());
        return modules;
    }

    @Override
    protected Stage launchGUI(final Injector injector) {
        FxHelloApplication.setInjector(injector);
        Application.launch(FxHelloApplication.class);
        return null;
    }
}
