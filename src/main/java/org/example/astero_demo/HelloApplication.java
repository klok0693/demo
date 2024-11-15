package org.example.astero_demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.astero_demo.initialization.CustomControllerFactory;

import java.io.IOException;

public class HelloApplication extends Application {
    private final CustomControllerFactory controllerFactory = new CustomControllerFactory();

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        final Scene scene = new Scene(fxmlLoader.load(), 1200, 720);

        stage.setTitle("untitled");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}