package org.example.astero_demo;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;

import javax.swing.*;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class SwingMain {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        AppConfiguration.INSTANCE.setUp();

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
        SwingUtilities.invokeLater(SwingHelloApplication::createAndShowGUI);
    }
}
