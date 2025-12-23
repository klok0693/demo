package org.example.astero_demo;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.AppConfiguration;

import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public class FxMain {

    public static void main(final String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        Application.launch(FxHelloApplication.class, args);
    }
}
