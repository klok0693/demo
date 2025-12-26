package org.example.astero_demo.func;

import com.google.inject.Injector;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.example.astero_demo.FxAppInitializer;
import org.example.astero_demo.FxHelloApplication;
import org.testfx.api.FxToolkit;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
public class FxTestAppInitializer extends FxAppInitializer {
    private final FxTestHelloApplication application;

    public FxTestAppInitializer(final FxTestHelloApplication application) {
        this.application = application;
    }

    @SneakyThrows
    @Override
    protected Stage launchGUI(final Injector injector) {
        FxHelloApplication.setInjector(injector);

        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> application);
        return null;
    }
}
