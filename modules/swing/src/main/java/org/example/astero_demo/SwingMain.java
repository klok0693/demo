package org.example.astero_demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;
import org.example.astero_demo.realization.initialization.di.module.CoreModule;
import org.example.astero_demo.swing.initialization.di.SwingModule;

import javax.swing.*;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class SwingMain {

    public static void main(String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        AppConfiguration.INSTANCE.setUp();
        SwingUtilities.invokeLater(SwingHelloApplication::createAndShowGUI);
    }
}
