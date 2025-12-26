package org.example.astero_demo.realization.configuration;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.realization.configuration.parser.ConfigurationParser;
import org.example.astero_demo.realization.configuration.preparer.Preparer;

/**
 * Main configuration class, delegation operations to stored
 * {@link Preparer} and {@link ConfigurationParser}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class AppConfigurationManager {
    private final Preparer initializer;
    private final ConfigurationParser parser;

    public AppConfigurationManager(
            final Preparer initializer,
            final ConfigurationParser parser) {
        this.initializer = initializer;
        this.parser = parser;
    }

    public void setUp(final String... args) {
        initializer.prepare();
        parser.parse(args);
    }
}
