package org.example.astero_demo.realization.configuration;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.example.astero_demo.realization.logging.MarkerStorage.INITIALIZATION_MARKER;

@Slf4j
public enum AppConfiguration implements Configuration {
    INSTANCE(BeforeLaunchConfiguration.INSTANCE, SystemPropertiesConfiguration.INSTANCE);

    private final List<Configuration> configurations;

    AppConfiguration(Configuration... configurations) {
        this.configurations = List.of(configurations);
    }

    @Override
    public void setUp() {
        log.debug(INITIALIZATION_MARKER, "Configure application");
        configurations.forEach(Configuration::setUp);
    }
}
