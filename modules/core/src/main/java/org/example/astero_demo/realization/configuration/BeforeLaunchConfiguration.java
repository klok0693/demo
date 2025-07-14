package org.example.astero_demo.realization.configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration, necessary to set up before {@link javafx.application.Application#launch} would be called
 */
@Slf4j
public enum BeforeLaunchConfiguration implements Configuration {
    INSTANCE;

    @Override
    public void setUp() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            log.error("Exception in thread " + thread.getName(), throwable);
        });
    }
}
