package org.example.astero_demo;

import com.formdev.flatlaf.FlatLightLaf;
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
 * Config, initialize and show Swing UI
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
@Slf4j
public class SwingHelloApplication {
    /**
     * Used 'protected' modificator to let test classes<p>
     * inherit and get access to application's component<p>
     * for tests
     */
    protected Injector injector;

    public void createAndShowGUI() {
        setupPLAF();
        initInjector();
        final SwingRootUI rootUI = builtRoot();
        final JFrame frame = builtJFrame(rootUI);
        addKeyHandler(frame);

        frame.pack();
        frame.setVisible(true);
    }

    private void setupPLAF() {
        FlatLightLaf.setup();
        //FlatDarkLaf.setup();
        //FlatIntelliJLaf.setup();
        //FlatDarculaLaf.setup();

        /*UIManager.setLookAndFeel(
                UIManager.createLookAndFeel("Metal")
                //.createLookAndFeel("FlatLaf Light")
                //.createLookAndFeel("Nimbus")
                // .getSystemLookAndFeelClassName()
        );*/
    }

    private void initInjector() {
        log.debug(INITIALIZATION_MARKER, "Init DI container");
        this.injector = Guice.createInjector(new CoreModule(), new SwingModule());
    }

    private SwingRootUI builtRoot() {
        return injector.getInstance(SwingRootUI.class);
    }

    private JFrame builtJFrame(final SwingRootUI rootUI) {
        log.debug(INITIALIZATION_MARKER, "Built root component");
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 750));
        //frame.setLocationRelativeTo(null);

        frame.getContentPane().add(rootUI);
        SwingUtilities.updateComponentTreeUI(frame);
        return frame;
    }

    private void addKeyHandler(final JFrame frame) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                injector.getInstance(SwingRootShortcutHandler.class).keyTyped(e);
            }
            return false;
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }
}