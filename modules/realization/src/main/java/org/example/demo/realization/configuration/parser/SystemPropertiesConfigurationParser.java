package org.example.demo.realization.configuration.parser;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.realization.context.ops.runtime.MutableConfiguration;

/**
 * System properties configuration
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class SystemPropertiesConfigurationParser extends AbstractChainParser {
    private static final String USER_DIR = "user.dir";

    public SystemPropertiesConfigurationParser(
            final MutableConfiguration configuration,
            final AppArgumentsParser argumentsParser) {
        super(configuration, argumentsParser);
    }

    @Override
    public void parseValues(final String... args) {
        log.debug("Working Directory: " + System.getProperty(USER_DIR));
    }
}
