package org.example.astero_demo.realization.configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum SystemPropertiesConfiguration implements Configuration {
    INSTANCE;

    @Override
    public void setUp() {
        log.debug("Working Directory: " + System.getProperty("user.dir"));
    }
}
