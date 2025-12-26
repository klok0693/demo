package org.example.astero_demo.realization.configuration.preparer;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration, necessary to set up before {@link javafx.application.Application#launch} would be called
 */
@Slf4j
public class BeforeLaunchPreparer implements Preparer {

    @Override
    public void prepare() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            log.error("Exception in thread " + thread.getName(), throwable);
        });
    }
}
