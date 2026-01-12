package org.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.example.demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * @author Pilip Yurchanka
 * @since 1.2
 */
@Slf4j
public class QtMain {

    public static void main(final String[] args) {
        log.debug(INITIALIZATION_MARKER, "Start application with args:{}", List.of(args));
        new QtAppInitializer().initialize(args);
    }
}
