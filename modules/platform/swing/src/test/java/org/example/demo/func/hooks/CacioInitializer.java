package org.example.demo.func.hooks;

import com.github.caciocavallosilano.cacio.ctc.junit.CacioExtension;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import static org.example.demo.util.logging.MarkerStorage.INITIALIZATION_MARKER;

/**
 * <a href="https://github.com/CaciocavalloSilano/caciocavallo?tab=readme-ov-file/">Cacio<a/>
 * graphics toolkit must be initialized before any other graphics component calls default
 * awt toolkit, to correctly swap graphics environment. Before JUnit5, initialization
 * was done by extending the JUnit with a {@link CacioExtension}. But, because we're also
 * using cucumber engine for JUnit5, this class used to manually launch execution logic,
 * stored in CacioExtension class
 *
 * @since 1.2
 * @author Pilip Yurchanka
 */

@Slf4j
public class CacioInitializer {
    private static final String NAME = "cacio";
    private static final CacioExtension extension;

    /**
     * Early initialization is a key to success graphics environment swap,
     * that's why static constructor is absolut necessary here
     */
    static {
        final String toolkit = System.getProperty("awt.toolkit");
        final String graphicsEnv = System.getProperty("java.awt.graphicsenv");
        log.debug(INITIALIZATION_MARKER, "Graphics toolkit: {}, environment: {}", toolkit, graphicsEnv);

        if (StringUtils.containsIgnoreCase(toolkit, NAME)) {
            /**
             * Initialization logic is hold in class constructor, so all we need to do
             * is to create a new instance of CacioExtension class
             */
            System.setProperty("cacio.managed.screensize", "1920x1080");
            extension = new CacioExtension();
        }
        else {
            extension = null;
        }
    }
}
