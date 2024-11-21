package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.configuration.BeforeLaunchConfiguration;
import org.example.astero_demo.realization.initialization.di.module.*;
import org.example.astero_demo.realization.initialization.di.module.ui.UIElementModule;
import org.example.astero_demo.realization.initialization.di.module.ui.UIAdapterModule;
import org.example.astero_demo.realization.initialization.di.module.ui.UIViewModule;
import org.example.astero_demo.realization.initialization.ui.CustomControllerFactory;
import org.example.astero_demo.realization.initialization.ui.NodeBuilderFactory;

import java.io.IOException;
import java.util.List;

import static org.example.astero_demo.realization.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class HelloApplication extends Application {

    @Override
    public void start(final Stage stage) throws IOException {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
        final Injector injector = Guice.createInjector(
                new AsyncModule(),
                new ProviderModule(),
                new ModelModule(),
                new InitializationModule(),
                new CommandModule(),
                new ControllerModule(),
                new UIAdapterModule(),
                new UIViewModule(),
                new UIElementModule());

        final CustomControllerFactory controllerFactory = injector.getInstance(CustomControllerFactory.class);
        final NodeBuilderFactory nodeFactory = injector.getInstance(NodeBuilderFactory.class);
        final LoadListener loadListener = injector.getInstance(LoadListener.class);

        log.debug(INITIALIZATION_MARKER, "Load root fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setBuilderFactory(nodeFactory);
        fxmlLoader.setLoadListener(loadListener);

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