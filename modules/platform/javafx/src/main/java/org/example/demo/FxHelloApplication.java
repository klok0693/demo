package org.example.demo;

import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.fx.port.keyboard.FxRootShortcutHandler;

import java.io.IOException;

import static org.example.demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and launch the application.
 * <p>
 * !!! Must not be in the same class with 'psvm' method
 * for correct initialization of JavaFX
 */
@Slf4j
public class FxHelloApplication extends Application {
    private static Injector injector;

    @Override
    public void start(final Stage stage) throws IOException {
        final Parent parent = builtRoot();
        final Scene scene = buildScene(parent);
        setKeyHandler(scene);

        stage.setTitle("FX Demo");
        stage.setScene(scene);
        stage.show();
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

    protected static Injector getInjector() {
        return injector;
    }

    public static void setInjector(final Injector inj) {
        injector = inj;
    }
}