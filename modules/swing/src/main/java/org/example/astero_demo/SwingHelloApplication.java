package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.initialization.di.module.CoreModule;
import org.example.astero_demo.swing.initialization.di.SwingModule;
import org.example.astero_demo.swing.port.ui.root.SwingRootUI;

import javax.swing.*;

import java.awt.*;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and launch the application
 */
@Slf4j
public class SwingHelloApplication {
    protected Injector injector;

    public static void createAndShowGUI() {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
        final Injector injector = Guice.createInjector(new CoreModule(), new SwingModule());

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

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 750));
        //frame.setLocationRelativeTo(null);

        frame.getContentPane().add(injector.getInstance(SwingRootUI.class));

        frame.pack();
        frame.setVisible(true);
    }


}