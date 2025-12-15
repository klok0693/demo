package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.initialization.di.module.*;
import org.example.astero_demo.swing.initialization.di.SwingModule;

import java.util.List;

import javax.swing.*;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and launch the application
 */
@Slf4j
public class HelloApplication {
    protected Injector injector;

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JLabel label = new JLabel();
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));

        AppConfiguration.INSTANCE.setUp();

        log.debug(INITIALIZATION_MARKER, "Init DI container");
        Injector injector = Guice.createInjector(new CoreModule(), new SwingModule());

/*        final Callback<Class<?>, Object> controllerFactory = injector.getInstance(Callback.class);
        final BuilderFactory nodeFactory = injector.getInstance(BuilderFactory.class);*/

        log.debug(INITIALIZATION_MARKER, "Load root fxml");
/*        final FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/root.fxml"));
        fxmlLoader.setControllerFactory(controllerFactory);
        fxmlLoader.setBuilderFactory(nodeFactory);

        final Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();*/

        SwingUtilities.invokeLater(HelloApplication::createAndShowGUI);
    }
}