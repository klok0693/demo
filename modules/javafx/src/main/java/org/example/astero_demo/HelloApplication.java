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
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.initialization.di.module.*;

import java.io.IOException;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class HelloApplication extends Application {
    protected Injector injector;

    @Override
    public void start(final Stage stage) throws IOException {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
/*        this.injector = Guice.createInjector(
                new AsyncModule(),
                new ProviderModule(),
                new ModelModule(),
                new InitializationModule(),
                new CommandModule(),
                new ControllerModule(),
                new LogicModule(),
                new UIAdapterModule(),
                new UIViewModule(),
                new UIElementModule());*/

        this.injector = Guice.createInjector(new CoreModule(), new FxModule());

        final Callback<Class<?>, Object> controllerFactory = injector.getInstance(Callback.class);
        final BuilderFactory nodeFactory = injector.getInstance(BuilderFactory.class);

        log.debug(INITIALIZATION_MARKER, "Load root fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setBuilderFactory(nodeFactory);

        final Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        stage.setTitle("untitled");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));

        AppConfiguration.INSTANCE.setUp();
        launch();
    }
}