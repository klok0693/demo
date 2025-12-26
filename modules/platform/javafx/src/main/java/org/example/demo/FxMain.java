package org.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.example.demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * @since 1.2
 * @author Pilip Yurchanka
 */
@Slf4j
public class FxMain {

    public static void main(final String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        new FxAppInitializer().initialize(args);
    }
}
