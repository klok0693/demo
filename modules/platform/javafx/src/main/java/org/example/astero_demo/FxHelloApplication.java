package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.fx.initialization.di.FxModule;
import org.example.astero_demo.fx.port.keyboard.FxRootShortcutHandler;
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.initialization.di.module.*;

import java.io.IOException;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and launch the application.
 * <p>
 * !!! Must not be in the same class with 'psvm' method
 * for correct initialization of JavaFX
 */
@Slf4j
@Getter
public class FxHelloApplication extends Application {
    private Injector injector;

    @Override
    public void start(final Stage stage) throws IOException {
        AppConfiguration.INSTANCE.setUp();

        createInjector();
        final Parent parent = builtRoot();
        final Scene scene = buildScene(parent);
        setKeyHandler(scene);

        stage.setTitle("FX Demo");
        stage.setScene(scene);
        stage.show();
    }

    private void createInjector() {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
        this.injector = Guice.createInjector(new CoreModule(), new FxModule());
    }

    private Parent builtRoot() throws IOException {
        final Callback<Class<?>, Object> controllerFactory = injector.getInstance(Callback.class);
        final BuilderFactory nodeFactory = injector.getInstance(BuilderFactory.class);

        log.debug(INITIALIZATION_MARKER, "Load root fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(FxHelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setBuilderFactory(nodeFactory);
        return fxmlLoader.load();
    }

    private Scene buildScene(final Parent parent) {
        return new Scene(parent, 1200, 720);
    }

    private void setKeyHandler(final Scene scene) {
        scene.setOnKeyPressed(injector.getInstance(FxRootShortcutHandler.class));
    }
}