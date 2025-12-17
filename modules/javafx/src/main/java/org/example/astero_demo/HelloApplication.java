package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.BuilderFactory;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.fx.initialization.di.FxModule;
import org.example.astero_demo.realization.initialization.di.module.*;

import java.io.IOException;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and launch the application
 */
@Slf4j
public class HelloApplication extends Application {
    protected Injector injector;

    @Override
    public void start(final Stage stage) throws IOException {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
        this.injector = Guice.createInjector(new CoreModule(), new FxModule());

        final Callback<Class<?>, Object> controllerFactory = injector.getInstance(Callback.class);
        final BuilderFactory nodeFactory = injector.getInstance(BuilderFactory.class);

        log.debug(INITIALIZATION_MARKER, "Load root fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setBuilderFactory(nodeFactory);

        final Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }
}