package org.example.demo;

import com.formdev.flatlaf.FlatLightLaf;
import com.google.inject.Injector;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.realization.context.ops.runtime.Configuration;
import org.example.demo.swing.port.keyboard.SwingRootShortcutHandler;
import org.example.demo.swing.port.ui.root.SwingRootUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.example.demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Config, initialize and show Swing UI
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */
@Slf4j
@Getter
public class SwingHelloApplication implements HelloApplication<JFrame> {
    /**
     * Used 'protected' modificator to let test classes<p>
     * inherit and get access to application's component<p>
     * for tests
     */
    protected Configuration configuration;
    protected Injector injector;
    private JFrame frame;

    public SwingHelloApplication(
            final Configuration configuration,
            final Injector injector) {
        this.configuration = configuration;
        this.injector = injector;
    }

    @Override
    public JFrame launchGUI() {
        if (configuration.isCustomSkinsAllowed()) {
            setupPLAF();
        }

        this.frame = builtJFrame(builtRoot());
        addKeyHandler(frame);

        frame.pack();
        frame.setVisible(true);
        return frame;
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

    private SwingRootUI builtRoot() {
        return injector.getInstance(SwingRootUI.class);
    }

    private JFrame builtJFrame(final SwingRootUI rootUI) {
        log.debug(INITIALIZATION_MARKER, "Built root component");
        this.frame = new JFrame();
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