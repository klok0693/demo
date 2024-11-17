package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.astero_demo.initialization.CustomControllerFactory;
import org.example.astero_demo.initialization.NodeBuilderFactory;
import org.example.astero_demo.initialization.di.*;

import java.io.IOException;

public class HelloApplication extends Application {
    //private final CustomControllerFactory controllerFactory = new CustomControllerFactory();

    @Override
    public void start(final Stage stage) throws IOException {

        Injector injector = Guice.createInjector(
                new ProviderModule(),
                new ModelModule(),
                new InitializationModule(),
                new CommandModule(),
                new ControllerModule(),
                new UIModule());

        CustomControllerFactory factory = injector.getInstance(CustomControllerFactory.class);

        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(factory);
        fxmlLoader.setBuilderFactory(new NodeBuilderFactory());
        final Scene scene = new Scene(fxmlLoader.load(), 1200, 720);

        stage.setTitle("untitled");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}