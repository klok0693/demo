package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.initialization.di.module.CoreModule;
import org.example.astero_demo.swing.initialization.di.SwingModule;
import org.example.astero_demo.swing.port.keyboard.SwingRootShortcutHandler;
import org.example.astero_demo.swing.port.ui.root.SwingRootUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

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

        log.debug(INITIALIZATION_MARKER, "Built root fxml");

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 750));
        //frame.setLocationRelativeTo(null);

        frame.getContentPane().add(injector.getInstance(SwingRootUI.class));
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                injector.getInstance(SwingRootShortcutHandler.class).keyTyped(e);
            }
            return false;
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        SwingUtilities.updateComponentTreeUI(frame);
        frame.pack();
        frame.setVisible(true);
    }


}