package org.example.astero_demo.realization.configuration;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.example.astero_demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * Main configuration class, delegation operations to stored {@link Configuration}
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public enum AppConfiguration implements Configuration {
    INSTANCE(BeforeLaunchConfiguration.INSTANCE, SystemPropertiesConfiguration.INSTANCE);

    private final List<Configuration> configurations;

    AppConfiguration(final Configuration... configurations) {
        this.configurations = List.of(configurations);
    }

    @Override
    public void setUp() {
        log.debug(INITIALIZATION_MARKER, "Configure application");
        configurations.forEach(Configuration::setUp);
    }
}
