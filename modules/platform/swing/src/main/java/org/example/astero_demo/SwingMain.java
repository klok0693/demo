package org.example.astero_demo;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;

import javax.swing.*;
import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class SwingMain {

    public static void main(String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        SwingUtilities.invokeLater(() -> new SwingHelloApplication().createAndShowGUI());
    }
}
